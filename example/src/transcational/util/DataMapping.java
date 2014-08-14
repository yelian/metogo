package transcational.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import transcational.annotation.Column;

public class DataMapping<T> {

	public List<T> getDate(Class<T> clazz, ResultSet rs){
		try {
			while(rs.next()){
				Field[] fields = clazz.getDeclaredFields();
				for(Field field : fields){
					String columnName = field.getAnnotation(Column.class).name();
					Class clazzField = field.getType();
					String v = rs.getString(columnName.toUpperCase());
					System.out.print(v + "\t");
				}
				System.out.println("\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(Field field: clazz.getDeclaredFields()){
			System.out.println(field.getName() + " : " + field.getType());
		}
		return null;
	}
}
