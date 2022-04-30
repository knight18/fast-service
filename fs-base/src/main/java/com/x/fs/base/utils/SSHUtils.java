package com.x.fs.base.utils;

import com.jcraft.jsch.*;
import com.x.fs.base.dto.ConnectInfoDTO;
import com.x.fs.base.dto.SSHExecuteResult;
import com.x.fs.common.exception.FsServiceException;

import java.io.*;

/**
 * 远程连接工具
 *
 * @author x
 */
public class SSHUtils {


    /**
     * 打开SSH会话
     *
     * @param connectInfo
     * @param timeout
     * @return
     */
    public static Session openSession(ConnectInfoDTO connectInfo, int timeout) {
        JSch jSch = new JSch();
        Session session = null;
        try {
            session = jSch.getSession(connectInfo.getUserName(), connectInfo.getHost(), connectInfo.getPort());
            session.setPassword(connectInfo.getPassWord());
            session.setTimeout(timeout);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
        } catch (JSchException e) {
            if (session != null) {
                try {
                    session.disconnect();
                } catch (Exception exception) {
                    e.printStackTrace();
                }
            }
            throw new FsServiceException("连接远程主机[" + connectInfo.getHost() + ":" + connectInfo.getPort() + "]失败");
        }
        return session;
    }


    /**
     * 打开Shell窗口
     *
     * @param session
     * @param inputStream
     * @param outputStream
     * @param timeout
     * @return
     */
    public static ChannelShell openChannelShell(Session session, InputStream inputStream, OutputStream outputStream, int timeout) {
        ChannelShell channelShell = null;
        try {
            // 打开渠道
            channelShell = (ChannelShell) session.openChannel("shell");
            channelShell.setInputStream(inputStream);
            channelShell.setOutputStream(outputStream);
            channelShell.connect(timeout);
        } catch (JSchException e) {
            if (channelShell != null) {
                try {
                    channelShell.disconnect();
                } catch (Exception exception) {
                    e.printStackTrace();
                }
            }
            throw new FsServiceException("打开Shell窗口失败[" + session.getHost() + ":" + session.getPort() + "]");
        }
        return channelShell;
    }

    /**
     * ssh登录执行命令
     *
     * @param connectInfoDTO 连接信息
     * @param cmd            命令
     * @param timeout        超时时间
     * @return 执行器
     */
    public static SSHExecuteResult execCommand(ConnectInfoDTO connectInfoDTO, String cmd, int timeout) {
        JSch jSch = new JSch();
        Session session = null;
        BufferedReader runStream = null;
        BufferedReader errStream = null;
        ChannelExec channelExec = null;
        SSHExecuteResult sshExecResult = new SSHExecuteResult();
        try {
            // 获取 ssh session
            session = jSch.getSession(connectInfoDTO.getUserName(), connectInfoDTO.getHost(), connectInfoDTO.getPort());
            session.setPassword(connectInfoDTO.getPassWord());
            session.setTimeout(timeout);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            // 通过 exec 方式执行
            channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand(cmd);
            channelExec.connect();
            // 获取输出流
            runStream = new BufferedReader(new InputStreamReader(channelExec.getInputStream()));
            errStream = new BufferedReader(new InputStreamReader(channelExec.getErrStream()));
            // 获取日志
            sshExecResult.setRunLog(getLog(runStream));
            sshExecResult.setErrLog(getLog(errStream));
            // 执行状态
            sshExecResult.setExitStatus(channelExec.getExitStatus());
            sshExecResult.setClosed(channelExec.isClosed());
        } catch (Exception e) {
            sshExecResult.setExitStatus(-1);
            sshExecResult.setClosed(true);
            sshExecResult.setRunLog("");
            sshExecResult.setErrLog(e.getMessage());
        } finally {
            try {
                if (runStream != null) {
                    runStream.close();
                }
                if (errStream != null) {
                    errStream.close();
                }
                if (channelExec != null) {
                    channelExec.disconnect();
                }
                if (session != null) {
                    session.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sshExecResult;
    }

    /**
     * 从流中读取日志
     *
     * @param br
     * @return
     * @throws IOException
     */
    private static String getLog(BufferedReader br) throws IOException {
        StringBuilder log = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            log.append(line).append("\n");
        }
        return log.toString();
    }

}
