package Utils;
import org.apache.log4j.Logger;

public class LogUtil {
	public static Logger logger=Logger.getLogger(LogUtil.class.getName());
	public static void starttestcase(String testcase )
	{
		logger.info("<================================================ Starting Execution Test Case --> " + testcase+"====================================================>");
	}
	public static void endtestcase(String testcase )
	{
		logger.info("<================================================ End of Test Case --> " + testcase+"====================================================>");
	}
	public static void info(String message)
	{
		logger.info(message);
	}
	public static void debug(String message)
	{
		logger.debug(message);
	}
	public static void error(String message)
	{
		logger.error(message);
	}
	public static void warn(String message)
	{
		logger.warn(message);
	}
	public static void fatal(String message)
	{
		logger.fatal(message);
	}

}
