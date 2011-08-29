/*
 * This file is part of Spoutcraft Launcher (http://wiki.getspout.org/).
 * 
 * Spoutcraft Launcher is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spoutcraft Launcher is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.spoutcraft.launcher.Logging;

import java.io.File;
import java.io.PrintStream;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import org.spoutcraft.launcher.PlatformUtils;

public class SystemConsoleListener {
	
	public void initialize() throws Exception {
	        LogManager logManager = LogManager.getLogManager();
	        logManager.reset();
	        
	        new File(PlatformUtils.getWorkingDirectory() + "Logs").mkdirs();
	        
	        Handler fileHandler = new FileHandler(PlatformUtils.getWorkingDirectory() + "Logs" + File.separator + "log", 10000, 5, true);
	        fileHandler.setFormatter(new ClientLoggerFormatter());
	        Logger.getLogger("").addHandler(fileHandler);   
	       
	    	PrintStream stdout = System.out;
	    	/*PrintStream stderr = System.err;*/
	    	
	    	Handler ConsoleHandle = new StreamHandler(stdout, new ClientLoggerFormatter());
	    	Logger.getLogger("").addHandler(ConsoleHandle);   
	    	
	    	/*Handler ErrHandle = new StreamHandler(stderr, new ClientLoggerFormatter());
	    	Logger.getLogger("").addHandler(ErrHandle);  */
	    	
	    	Logger logger;
	    	SystemListenerStream los;

	    	logger = Logger.getLogger("stdout");
	    	los = new SystemListenerStream(logger, SystemListenerLevel.STDOUT);
	    	System.setOut(new PrintStream(los, true));
	    	
	    	/*logger = Logger.getLogger("stderr");
	    	los= new SystemListenerStream(logger, SystemListenerLevel.STDERR);
	    	System.setErr(new PrintStream(los, true));*/
	    	
	}
}
