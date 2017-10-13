package com.bonc.quickframework.generator.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ShellHelper {

	public static boolean isWindow=false;
	static List<String> commands;

	static {
		commands = new LinkedList<String>();
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		if (os.startsWith("win") || os.startsWith("Win")) {
			commands.add("cmd.exe");
			commands.add("/c");
			isWindow=true;
		} else {
			commands.add("/bin/sh");
			commands.add("-c");
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		String result = ShellHelper.excuteCommandFile("/home/song/dev2pm/redeploy.sh");

		System.err.println(result);
	}
	
	/**
	 * 执行脚本文件
	 * @param commandfile 脚本文件（bat或者shell）
	 * @return
	 */
	public static String excuteCommandFile(String commandfile) throws InterruptedException {
		StringBuffer stringBuffer = new StringBuffer();
		try {
			Process p =null;
			if(isWindow){
				p = Runtime.getRuntime().exec(commandfile);
			}else {
				Runtime.getRuntime().exec("chmod 777 "+ commandfile).waitFor();;
				p = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",commandfile},null,null);
				p.waitFor();
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream(), "utf-8"));// 注意中文编码问题
			//p.waitFor(10,TimeUnit.SECONDS);
			String line;
			while ((line = br.readLine()) != null) {
				stringBuffer.append(line).append("\r\n");
			}
			br.close();
		} catch (IOException ioe) {
			stringBuffer.append("execute shell error：\r\n")
					.append(ioe.getMessage()).append("\r\n");
		}
		return stringBuffer.toString();
	}

	/**
	 * 执行本地命令
	 */
	public static String exectLocalCommand(String... execCommands)
			throws IOException {
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			Process pid = null;
			List<String> cmdList = new LinkedList<String>(commands);
			for (String cmd : execCommands) {
				cmdList.add(cmd);
			}
			String[] shells = (String[]) cmdList.toArray(new String[cmdList
					.size()]);
			pid = Runtime.getRuntime().exec(shells);
			if (pid != null) {
				reader = new BufferedReader(new InputStreamReader(
						pid.getInputStream(), "gbk"), 1024);
				pid.waitFor();
			} else {
				String line = null;
				// 读取Shell的输出内容，并添加到stringBuffer中
				while (reader != null && (line = reader.readLine()) != null) {
					stringBuffer.append(line).append("\r\n");
				}
			}
		} catch (Exception ioe) {
			stringBuffer.append("execute shell error：\r\n")
					.append(ioe.getMessage()).append("\r\n");
		} finally {
			String line;
			while ((line = reader.readLine()) != null)
				stringBuffer.append(line).append("\r\n");
		}
               
		return stringBuffer.toString();
	}
}
