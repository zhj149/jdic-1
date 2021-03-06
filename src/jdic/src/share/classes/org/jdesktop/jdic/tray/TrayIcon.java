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

package org.jdesktop.jdic.tray;


import org.jdesktop.jdic.tray.internal.ServiceManager;
import org.jdesktop.jdic.tray.internal.TrayIconService;

import java.awt.Point;
import javax.swing.Icon;
import javax.swing.JPopupMenu;
import java.awt.event.ActionListener;


/**
 *  The <code>TrayIcon</code> class represents a tray Icon that can be added to 
 *  a System tray. A TrayIcon can have a caption (text), an Icon and a menu 
 *  associated with it. In addition a TrayIcon can also have a tooltip 
 *  associated that is displayed when the mouse hovers over the TrayIcon.
 *  <p>
 *  TrayIcons will display the specified Menu when a right mouse click
 *  is performed on the TrayIcon. Left button clicking will trigger
 *  ActionEvents.
 */


public class TrayIcon {

    TrayIconService tis;

    /**
     * TrayIcon constructor - creates a TrayIcon with the specified Icon
     *
     */
    public TrayIcon(Icon i) {
        this(i, null, null); 
    }
    
    /**
     * TrayIcon constructor - creates a TrayIcon with the specified Icon
     * and the specified caption.
     * @param icon Icon
     * @param caption caption to use
     */

    public TrayIcon(Icon icon, String caption) {
        this(icon, caption, null); 
    }

    /**
     * TrayIcon constructor - creates a TrayIcon with the specified Icon
     * the specified caption and Popup menu.
     * @param icon Icon
     * @param tooltip tooltip to use
     * @param popup popupMenu to use
     */
    public TrayIcon(Icon icon, String tooltip, JPopupMenu popup) {
        tis = (TrayIconService) 
            ServiceManager.getService(ServiceManager.TRAY_ICON_SERVICE);
        setIcon(icon);
        setToolTip(tooltip);
        setPopupMenu(popup); 
    }

    /**
     * Sets the icon for this TrayIcon.
     * 
     * @param icon icon to use
     */

    public void setIcon(Icon icon) {
        if (tis != null) {
            tis.setIcon(icon);
        }
    }

    /**
     * Sets the PopupMenu for this TrayIcon.
     * 
     * @param  popup Popup
     */

    public void setPopupMenu(JPopupMenu popup) {
        if (tis != null) {
            tis.setPopupMenu(popup);
        }
    }

    /**
     * Sets the ToolTip string for this TrayIcon.
     * For windows,it can have a maximum of 64 characters including the terminating NULL.
     * @param tooltip
     */
    public void setToolTip(String tooltip) {
    	if(tis !=null){
    		tis.setToolTip(tooltip);
    	}        
    }

    /**
     * Adds the specified ActionListener to the list of ActionListeners
     * for this component.
     * 
     * @param  listener
     */
    public void addActionListener(ActionListener listener) {
        if (tis != null) {
            tis.addActionListener(listener);
        } 
    } 

    /**
     * Removes the specified ActionListener from the list of ActionListeners
     * for this component.
     * 
     * @param  listener
     */
    public void removeActionListener(ActionListener listener) {
        if (tis != null) {
            tis.removeActionListener(listener);
        } 
    } 

    /**
     * Sets the caption for this TrayIcon.
     * 
     * @param caption
     */
    public void setCaption(String caption) {
        if (tis != null) {
            tis.setCaption(caption);
        }
    }

    /**
     *
     * Sets the auto-size property. 
     * Auto-size determines whether the tray icon is automatically
     * sized to fit the actual tray size.
     *
     */
    public void setIconAutoSize(boolean autosize) {
        if (tis != null) {
            tis.setIconAutoSize(autosize);
        }
    }

    TrayIconService getTrayIconService() {
        return tis;
    }

    /**
     * Gets the location on screen for this TrayIcon
     * @return Point 
     */
    public Point getLocationOnScreen() {
        Point p = null;

        if (tis != null) {
            p = tis.getLocationOnScreen();
        }

        return p;
    }

    /**
     * Balloon Message types. Used by <code>displayMessage(String, String, int)</code>
     * to determine what icon to display.<p>
     */
    /** Used for information messages. */
    public static final int INFO_MESSAGE_TYPE = 0;
    /** Used for error messages. */
    public static final int ERROR_MESSAGE_TYPE = 1;
    /** Used for warning messages. */
    public static final int WARNING_MESSAGE_TYPE = 2;
    /** No icon is used. */
    public static final int NONE_MESSAGE_TYPE = 3;

    /**
     * Displays a popup message near the tray icon. 
     * The message will disappear after a timeout or if user clicks on it.
     * <p>
     * Either the caption or the text may be null, 
     * but an NullPointerException is thrown if both are null.
     * <p>
     * <b>Note</b>: Some platforms may not support showing a message.
     * <p>
     * @param caption the caption displayed above the text, usually in bold; may be null
     * @param text the text displayed for the particular message; may be null
     * @param type the type of message to be displayed:
     *  <code>INFO_MESSAGE_TYPE</code>,    <code>ERROR_MESSAGE_TYPE</code>,
     *  <code>WARNING_MESSAGE_TYPE</code>, <code>NONE_MESSAGE_TYPE</code>
     * 
     * @throws NullPointerException  If both caption and text are null
     */
    public void displayMessage(String caption, String text, int type)throws NullPointerException{
    	if(caption == null && text == null)
    		throw new NullPointerException("Both caption and text are null.");
    	if(tis != null)
    		tis.showBalloonMessage(caption, text, type);
    }
    
    /**
     * Adds the specified ActionListener to the list of ActionListeners
     * for this trayicon's balloon message.
     * @param listener
     */
    public void addBalloonActionListener(ActionListener listener) {
        if (tis != null) {
            tis.addBalloonActionListener(listener);
        } 
    }
    /**
     * Removes the specified ActionListener from the list of ActionListeners
     * for this trayicon's balloon message.
     * 
     * @param  listener
     */
    public void removeBalloonActionListener(ActionListener listener){
        if (tis != null) {
            tis.removeBalloonActionListener(listener);
        } 
    }
}
