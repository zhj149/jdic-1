/*
 * Copyright (C) 2004 Sun Microsystems, Inc. All rights reserved. Use is
 * subject to license terms.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 */

package org.jdesktop.jdic.browser;

import org.jdesktop.jdic.init.JdicInitException;
import org.jdesktop.jdic.init.JdicManager;

/**
 * Handles the communication with Mozilla's Gecko Runtime Engine (GRE).
 * 
 * @author Alexander Hars, Inventivio GmbH
 */
class WebKitEngine implements IBrowserEngine {

	private static final String WEBKIT = BrowserEngineManager.WEBKIT;
	/** Whether the Mozilla/GRE is available */
	private static boolean isEngineAvailable = true;

	/**
	 * Checks whether the necessary modifications to the environment have been
	 * made in order to embed Mozilla
	 */
	private boolean isEnvironmentPrepared = false;

	/** Path to the mozilla executable. Irrelevant on windows. */
	public static String envMFH;

	private static String browserBinary;

	/**
	 * Returns the standardadized name of the embedded browser engine. The name
	 * identifies the browser type but not the browser version. This name should
	 * be the same for all browser engines that embed the same browser,
	 * independent of platform. Names should be such that they can be presented
	 * in an English user dialog for choosing their favorite embedded engine.
	 * They may have spaces but they may not be internationalized.
	 * 
	 * Examples for the standardized names are: Internet Explorer, Mozilla,
	 * 
	 * @return The standardized name of the embedded browser engine. May not be
	 *         null or empty.
	 */
	public String getBrowserName() {
		return WEBKIT;
	}

	/**
	 * Additional information about the specific type and version of embedded
	 * browser component being used.
	 * 
	 * @return A string indicating the specific version of the browser or
	 *         browser component which is being embedded, null or an empty
	 *         string.
	 */
	public String getBrowserVersion() {
		return "";
	}

	/**
	 * Checks whether the associated Engine is available on the current system.
	 * This is a prerequisite for creating <code>Browser</code> instances on
	 * the current system.
	 * 
	 * @return true if an engine is available, false if no engine is available,
	 *         the engine is not found or can not be accessed.
	 */
	public boolean isEngineAvailable() {
		return isEngineAvailable;
	}

	/**
	 * Checks whether the default BrowserPath indicates that this browser is set
	 * as the default browser on the current system.
	 * 
	 * @param defaultBrowserPath
	 *            taken from the OS or whereever
	 * @return true if this BrowserEngine represents the default browser, true
	 *         otherwise
	 */
	public boolean isDefaultBrowser(String browserPath) {
		if (browserPath == null) {
			return false;
		} else {
			return (browserPath
					.indexOf("/System/Library/Frameworks/WebKit.framework/WebKit") >= 0);
		}
	}

	/**
	 * Returns the name of binary file for the embedded browser component.
	 * 
	 * @return Path to the binary file or null if no such path exists TODO how
	 *         do we treat a JEditorPane? or a PureJava component?
	 */
	public String getEnginePath() {
		//TODO autogenerated-method stub
		return null;
	}

	/**
	 * prepares the environment. Returns false if the preparation was not
	 * successful. Checks whether Mozilla is available and sets environment
	 * variables as needed. Sets the isAvailable flag. Sets the browserBinary
	 * TODO ideally, no environment variables should be set here. They should
	 * only be set when Mozilla is actually used.
	 */
	protected boolean prepareEnvironment() {
		return true;
	}

	public String getBrowserBinary() {
		return browserBinary;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jdesktop.jdic.browser.IBrowserEngine#getEmbeddedBinaryName()
	 */
	public String getEmbeddedBinaryName() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jdesktop.jdic.browser.IBrowserEngine#setEnginePath(java.lang.String)
	 */
	public void setEnginePath(String fullPath) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jdesktop.jdic.browser.IBrowserEngine#initialize()
	 */
	public void initialize() throws JdicInitException {
	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jdesktop.jdic.browser.IBrowserEngine#getCharsetName()
	 */
	public String getCharsetName() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jdesktop.jdic.browser.IBrowserEngine#getFileProtocolURLPrefix()
	 */
	public String getFileProtocolURLPrefix() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jdesktop.jdic.browser.IBrowserEngine#isInitialized()
	 */
	public boolean isInitialized() {
		return false;
	}
	
	/**
	 * return webkit browser under mac
	 */
	public IWebBrowser getWebBrowser() {
		// this is mac os specified browser,which is not accessible under win/unix
		String webkitbrowser = "org.jdesktop.jdic.browser.WebKitWebBrowser";
		try {
			return (IWebBrowser) Class.forName(webkitbrowser).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
