package Utils;

import org.apache.log4j.Logger;

public class LogsUtil {
public static Logger logger=Logger.getLogger(LogsUtil.class.getName());
	
	public static void info(String message)
	{
		logger.info(message);
	}
	public static void error(String message)
	{
		logger.error(message);
	}
	public static void fatal(String message)
	{
		logger.fatal(message);
	}
	public static void warn(String message)
	{
		logger.warn(message);
	}
}
