/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * MentaBean => http://www.mentabean.org
 * Author: Sergio Oliveira Jr. (sergio.oliveira.jr@gmail.com)
 */
package org.mentabean.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mentabean.DBType;

public class TimestampType implements DBType<java.util.Date> {

	private boolean canBeNull = true;

	@Override
	public boolean canBeNull() {
		return canBeNull;
	}

	public TimestampType nullable(boolean flag) {
		TimestampType d = new TimestampType();
		d.canBeNull = flag;
		return d;
	}

	@Override
	public String getAnsiType() {
		return "timestamp";
	}

	@Override
	public String toString() {

		return this.getClass().getSimpleName();
	}

	@Override
	public java.util.Date getFromResultSet(final ResultSet rset, final int index) throws SQLException {

		return rset.getTimestamp(index);
	}

	@Override
	public java.util.Date getFromResultSet(final ResultSet rset, final String field) throws SQLException {

		return rset.getTimestamp(field);
	}

	@Override
	public Class<? extends Object> getTypeClass() {

		return java.util.Date.class;
	}

	@Override
	public void bindToStmt(final PreparedStatement stmt, final int index, final java.util.Date value) throws SQLException {

		if (value == null) {

			stmt.setTimestamp(index, null);

		} else if (value instanceof java.sql.Timestamp) {

			final java.sql.Timestamp t = (java.sql.Timestamp) value;

			stmt.setTimestamp(index, t);

		} else {

			stmt.setTimestamp(index, new java.sql.Timestamp(value.getTime()));

		}
	}
}