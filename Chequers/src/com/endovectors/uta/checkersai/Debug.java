/* Debug.java : Holds the debug state
 * Copyright (C) 1998-2002  Paulo Pinto
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

package com.endovectors.uta.checkersai;
public class Debug {

    /**
     * Flag de debug
     */
    private static boolean debugIsOn = false;

    /**
     * Escreve uma mensagem de debug se a flag de
     * debug for verdadeira.
     * @param msg Mensagem de debug
     */
    public static void println (String msg) {
	if (debugIsOn)
	    System.out.println (msg);
    }

    /**
     * Muda o valor da flag de debug
     * @param value Novo valor da flag de debug
     */
    public static void setDebug (boolean value) {
	debugIsOn = value;
    }

    /**
     * Indica qual o valor da flag de debug
     * @return O valor actual da flag de debug
     */
    public static boolean isDebugOn () {
	return debugIsOn;
    }
}
