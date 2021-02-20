package com.qing.hu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.CaseUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class jpa {
	static Workbook xssfWorkbook;
	static Sheet sheet;
	static Row row;
	static StringBuilder data;
	static Map culumnTypeToJava = new HashMap();
	static List columnNeedLength = new ArrayList();
	static List commonColumns = new ArrayList();
	static String className;
	static String tableName;
	static String classNameForUrl;

	public static void main(String[] args) throws Exception {
		culumnTypeToJava.put("char", "String");
		culumnTypeToJava.put("varchar", "String");
		culumnTypeToJava.put("varchar2", "String");
		culumnTypeToJava.put("text", "String");
		culumnTypeToJava.put("longtext", "String");
		culumnTypeToJava.put("int", "Integer");
		culumnTypeToJava.put("number", "Integer");
		culumnTypeToJava.put("datetime", "Timestamp");
		culumnTypeToJava.put("date", "Date");

		columnNeedLength.add("char");
		columnNeedLength.add("varchar");
		columnNeedLength.add("varchar2");

		commonColumns.add("UPD_TIME");
		commonColumns.add("UPD_USER_ID");
		commonColumns.add("INS_TIME");
		commonColumns.add("INS_USER_ID");
		commonColumns.add("DEL_FLG");

		File file = new File("src\\main\\resources\\db\\ms.xlsx");
		InputStream stream = new FileInputStream(file);
		xssfWorkbook = new XSSFWorkbook(stream);
		System.out.println(xssfWorkbook.getNumberOfSheets());
		int numOfSheets = xssfWorkbook.getNumberOfSheets();
		for (int i = 0; i < numOfSheets; i++) {
			//if (xssfWorkbook.getSheetAt(i).getSheetName().equals("SYS_USER")) {
				write(i);
			//}
		}
		System.out.println("写入完成++++++++++++++++");
	}

	private static void write(int sheetNum) throws IOException {
		sheet = xssfWorkbook.getSheetAt(sheetNum);
		className = sheet.getSheetName();
		tableName = sheet.getSheetName().toLowerCase();
		classNameForUrl = tableName.replace("_", "");
		System.out.println(className);
		className = CaseUtils.toCamelCase(className, true, new char[] { '_' });
		System.out.println(className);
		
		writeEntity();
		//writeSQL();
		//writeSimpleEntity();
		
		//writeMapper();
		
		//writeIManager();
		//writeManagerImpl();
		
		//writeIService();
		//writeServiceImpl();
		
		// writeRepository();
		
		//writeController();
	}

	private static void writeController() throws IOException {
		data = new StringBuilder();
		String serviceName = Character.toLowerCase(className.charAt(0)) + className.substring(1) + "Service";
		data.append("package com.qing.hu.controller;\n" + "\n" + "import com.qing.hu.entity." + className + ";\n"
				+ "import com.qing.hu.service.I" + className + "Service;\n"
				+ "import org.springframework.beans.factory.annotation.Autowired;\n"
				+ "import org.springframework.web.bind.annotation.RequestMapping;\n"
				+ "import org.springframework.web.bind.annotation.RequestMethod;\n"
				+ "import org.springframework.web.bind.annotation.RestController;\n" + "\n"
				+ "import java.util.ArrayList;\n" + "import java.util.List;\n" + "\n" + "\n" + "@RestController\n"
				+ "@RequestMapping(\"/" + classNameForUrl + "\")\n" + "public class " + className + "Controller {\n"
				+ "    @Autowired\n" + "    I" + className + "Service " + serviceName + ";\n" + "\n"
				+ "    @RequestMapping(value = \"/getall\", method = RequestMethod.GET)\n" + "    public List<"
				+ className + "> get" + className + "List() {\n" + "        return " + serviceName + ".findAll();\n"
				+ "    }\n" + "}");
		File file = new File("src\\main\\java\\com\\qing\\hu\\controller\\" + className + "Controller.java");
		if (file.exists())
			file.delete();
		boolean b = file.createNewFile();
		if (b) {
			Writer out = new FileWriter(file);
			out.write(data.toString());
			out.close();
		}
	}

	private static void writeRepository() throws IOException {
		data = new StringBuilder();
		data.append("package com.qing.hu.repository;\n" + "\n" + "import com.qing.hu.entity." + className + ";\n"
				+ "import org.springframework.data.jpa.repository.JpaRepository;\n"
				+ "import org.springframework.stereotype.Component;\n" + "\n" + "@Component\n" + "public interface "
				+ className + "Repository extends JpaRepository<" + className + ", String> {\n" + "}");
		File file = new File("src\\main\\java\\com\\qing\\hu\\repository\\" + className + "Repository.java");
		if (file.exists())
			file.delete();
		boolean b = file.createNewFile();
		if (b) {
			Writer out = new FileWriter(file);
			out.write(data.toString());
			out.close();
		}
	}

	private static void writeMapper() throws IOException {
		data = new StringBuilder();

		data.append("package com.qing.hu.mapper;\n" + "\n" + "import com.qing.hu.entity." + className + ";\n"
				+ "import com.qing.hu.mapper.base.BaseMapper;\n" + "\n" + "@org.apache.ibatis.annotations.Mapper\n"
				+ "public interface " + className + "Mapper  extends BaseMapper<" + className + "> {\n" + "}");
		File file = new File("src\\main\\java\\com\\qing\\hu\\mapper\\" + className + "Mapper.java");
		if (file.exists())
			file.delete();
		boolean b = file.createNewFile();
		if (b) {
			Writer out = new FileWriter(file);
			out.write(data.toString());
			out.close();
		}
	}

	private static void writeEntity() throws IOException {
		data = new StringBuilder();
		data.append("package com.qing.hu.entity;\n\n" + "import com.qing.hu.entity.base.BaseEntity;\n"
				+ "import lombok.*;\n" + "import lombok.experimental.SuperBuilder;\n" + "\n"
				+ "import javax.persistence.Column;\n" + "import javax.persistence.Entity;\n"
				+ "import javax.persistence.Id;\n" + "import javax.persistence.Table;\n" + "import java.sql.*;\n" + "\n"
				+ "@Entity\n" + "@SuperBuilder\n" + "@Data\n" + "@Table\n"
				+ "@org.hibernate.annotations.Table(appliesTo = \"" + tableName + "\",comment=\""
				+ sheet.getRow(0).getCell(2) + "\")\n" + "@AllArgsConstructor\n" + "@NoArgsConstructor\n"
				+ "public class " + className + " extends BaseEntity {");

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			Cell cell = row.getCell(0);
			if (cell == null || cell.getCellType() != CellType.NUMERIC) {
				continue;
			}
			cell = row.getCell(1);
			//if (commonColumns.contains(cell.toString())) continue; //注释掉baseEntity
			writeRow();
		}
		data.append("\n}");
//        System.out.println(data);

		File file = new File("src\\main\\java\\com\\qing\\hu\\entity\\" + className + ".java");
		if (file.exists())
			file.delete();
		boolean b = file.createNewFile();
		if (b) {
			Writer out = new FileWriter(file);
			out.write(data.toString());
			out.close();
		}
	}
	private static void writeSimpleEntity() throws IOException {
		data = new StringBuilder();
		data.append("package com.qing.hu.entity;\n\n" + "import com.qing.hu.entity.base.BaseEntity;\n"
				 
				+ "public class " + className + " extends BaseEntity {");

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			Cell cell = row.getCell(0);
			if (cell == null || cell.getCellType() != CellType.NUMERIC) {
				continue;
			}
			cell = row.getCell(1);
			//if (commonColumns.contains(cell.toString())) continue;//注释掉baseEntity
			writeSimpleRow();
		}
		data.append("\n}");
//        System.out.println(data);

		File file = new File("src\\main\\java\\com\\qing\\hu\\entity\\" + className + ".java");
		if (file.exists())
			file.delete();
		boolean b = file.createNewFile();
		if (b) {
			Writer out = new FileWriter(file);
			out.write(data.toString());
			out.close();
		}
	}
	private static void writeSQL() throws IOException {
		System.out.println("----1-----------");
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			Cell cell = row.getCell(0);
			if (cell == null || cell.getCellType() != CellType.NUMERIC) {
				continue;
			}
			cell = row.getCell(1);
			if (commonColumns.contains(cell.toString()))
				continue;
			
			String reference=row.getCell(9).toString().replaceAll("\\\"|\\\'|\\\n", "");
			String rowName = row.getCell(1).toString();


			String definition = " comment on column "+tableName+"."+rowName+" is '"+reference+"'; ";
			System.out.println(definition);
 
		}

		
		String definition1 = " comment on column "+tableName+".UPD_TIME is '更新时间(更新日期)	'; ";
		System.out.println(definition1);
		
		String definition2 = " comment on column "+tableName+".UPD_USER_ID is '更新者ID'; ";
		System.out.println(definition2);
		
		String definition3 = " comment on column "+tableName+".INS_TIME is '作成时间(申请日期)	'; ";
		System.out.println(definition3);
		
		String definition4 = " comment on column "+tableName+".INS_USER_ID is '作成者ID	'; ";
		System.out.println(definition4);
		
		String definition5 = " comment on column "+tableName+".DEL_FLG is '删除FLG	0:未删除;1:已删除'; ";
		System.out.println(definition5);
		
		
		System.out.println("----2-----------");

	 
		
	}
	private static void writeRow() {
		String rowName = row.getCell(1).toString();

		rowName = CaseUtils.toCamelCase(rowName, false, new char[] { '_' });
		data.append("\n");
		data.append("\n");
		if ((row.getCell(5) + "").equals("*") || ((row.getCell(9) + "").startsWith("ID"))
				|| ((row.getCell(9) + "").startsWith("主键"))) {
			data.append("    @Id\n");
		}
		String definition = "    @Column(columnDefinition = \"";
		definition += row.getCell(3);
		if (columnNeedLength.contains(row.getCell(3) + "")) {
			definition += "(" + intStr(row.getCell(4)+"") + ")";
		}
		if (row.getCell(7).getCellType() != CellType.BLANK) {
			String defaultValue = row.getCell(7) + "";
			defaultValue = defaultValue.replace("\"", "");
			defaultValue = defaultValue.replace("\'", "");
			definition += " default '" + defaultValue + "' ";
		}
		//definition += " comment '" + row.getCell(9).toString().replaceAll("\\\"|\\\'|\\\n", ""); //oracle不能注释而去掉
		if (row.getCell(10).getCellType() != CellType.BLANK) {
			String reference = row.getCell(10).toString();
//            reference = reference.replaceAll("\\\"|\\\'|\\\n","");
			reference = reference.replace("\"", "");
			reference = reference.replace("\'", "");
			reference = reference.replace("\n", ",");
			definition += "/" + reference;
		}
		//definition += "'";//oracle不能注释而去掉
		definition += "\") \n";
		data.append(definition);
		data.append("    private " + culumnTypeToJava.get(row.getCell(3) + "") + " " + rowName + "; ");
	}
	private static void writeSimpleRow() {
		String rowName = row.getCell(1).toString();

		rowName = CaseUtils.toCamelCase(rowName, false, new char[] { '_' });
		data.append("\n");
		data.append("    private " + culumnTypeToJava.get(row.getCell(3) + "") + " " + rowName + "; ");
	}
	private static void writeIService() throws IOException {
		data = new StringBuilder();

		data.append("package com.qing.hu.service;\n" + "\n" + "import com.qing.hu.entity." + className + ";\n"
				+ "import com.qing.hu.service.base.IBaseService;\n" + "\n" + "public interface I" + className
				+ "Service  extends IBaseService<" + className + ", String, Object> {\n" + "}");
		File file = new File("src\\main\\java\\com\\qing\\hu\\service\\I" + className + "Service.java");
		if (file.exists())
			file.delete();
		boolean b = file.createNewFile();
		if (b) {
			Writer out = new FileWriter(file);
			out.write(data.toString());
			out.close();
		}
	}

	private static void writeServiceImpl() throws IOException {
		data = new StringBuilder();

		data.append("package com.qing.hu.service.impl;\n" + "\n" + "import com.qing.hu.entity." + className + ";\n"
				+ "import com.qing.hu.mapper." + className + "Mapper;\n"
				+ "import com.qing.hu.mapper.base.BaseMapper;\n" + "import com.qing.hu.service.base.impl.BaseService;\n"
				+ "import org.springframework.beans.factory.annotation.Autowired;"
				+ "import org.springframework.stereotype.Service;" + "import com.qing.hu.service.I" + className
				+ "Service;\n" + "\n" + "@Service\n" + "public class " + className + "Service  extends BaseService<"
				+ className + ", String, Object> implements I" + className + "Service {\n" + "    @Autowired \n"
				+ "    private " + className + "Mapper " + toLowerCaseFirstOne(className) + "Mapper; \n"
				+ "    // 重写BaseServiceImpl抽象方法，将当前Mapper返回 \n" + "    @Override \n" + "    protected BaseMapper<"
				+ className + "> getBaseMapper() { return " + toLowerCaseFirstOne(className) + "Mapper; } \n" + "\n"
				+ "}");
		File file = new File("src\\main\\java\\com\\qing\\hu\\service\\impl\\" + className + "Service.java");
		if (file.exists())
			file.delete();
		boolean b = file.createNewFile();
		if (b) {
			Writer out = new FileWriter(file);
			out.write(data.toString());
			out.close();
		}
	}

	private static void writeIManager() throws IOException {
		data = new StringBuilder();

		data.append("package com.qing.hu.manager;\n" + "\n" + "import com.qing.hu.entity." + className + ";\n"
				+ "import com.qing.hu.manager.base.IBaseManager;\n" + "\n" + "public interface I" + className
				+ "Manager  extends IBaseManager<" + className + ", String, Object> {\n" + "}");
		File file = new File("src\\main\\java\\com\\qing\\hu\\manager\\I" + className + "Manager.java");
		if (file.exists())
			file.delete();
		boolean b = file.createNewFile();
		if (b) {
			Writer out = new FileWriter(file);
			out.write(data.toString());
			out.close();
		}
	}

	private static void writeManagerImpl() throws IOException {
		data = new StringBuilder();

		data.append("package com.qing.hu.manager.impl;\n" + "\n" + "import com.qing.hu.entity." + className + ";\n"
				+ "import com.qing.hu.mapper." + className + "Mapper;\n"
				+ "import com.qing.hu.mapper.base.BaseMapper;\n" + "import com.qing.hu.manager.base.impl.BaseManager;\n"
				+ "import org.springframework.beans.factory.annotation.Autowired;"
				+ "import org.springframework.stereotype.Service;" + "import com.qing.hu.manager.I" + className
				+ "Manager;\n" + "\n" + "@Service\n" + "public class " + className + "Manager  extends BaseManager<"
				+ className + ", String, Object> implements I" + className + "Manager {\n" + "    @Autowired \n"
				+ "    private " + className + "Mapper " + toLowerCaseFirstOne(className) + "Mapper; \n"
				+ "    // 重写BaseManagerImpl抽象方法，将当前Mapper返回 \n" + "    @Override \n" + "    protected BaseMapper<"
				+ className + "> getBaseMapper() { return " + toLowerCaseFirstOne(className) + "Mapper; } \n" + "\n"
				+ "}");
		File file = new File("src\\main\\java\\com\\qing\\hu\\manager\\impl\\" + className + "Manager.java");
		if (file.exists())
			file.delete();
		boolean b = file.createNewFile();
		if (b) {
			Writer out = new FileWriter(file);
			out.write(data.toString());
			out.close();
		}
	}
	// 首字母转小写
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}
	
	//字符串去掉小数点后取整数
	public static String intStr(String str) {
		//String str = "70.2";
		 if(str.contains(".")) {
			 int indexOf = str.indexOf(".");
			 str = str.substring(0, indexOf);
		 }
		 return str;
	}

	
}
