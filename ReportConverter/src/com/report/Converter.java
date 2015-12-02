package com.report;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Converter {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		
//		String[] col={"state","sales"};
		Converter c=new Converter();
		c.parseXml();
//		c.saveFile(getBIRTXml(col, "States vs Sales", new Random().nextInt()));
	}
	
	private String[] parseXml() throws SAXException, IOException, ParserConfigurationException{
		String[] str=new String[3];
		File fXmlFile = new File("D:/Projects/Report Conversion/Cognos.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
				
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("v2_chartTitle");
		System.out.println(nList.item(0).getChildNodes().item(1).getChildNodes().item(0).getChildNodes().item(0).getTextContent());
		return str;
	}

	private void saveFile(String xmlStr){
		try {
			Document doc = convertStringToDocument(xmlStr);
			Source source = new DOMSource(doc);
			Result result = new StreamResult(new File("D:\\Projects\\Report Conversion\\BirtRepoprt.xml"));
			Transformer xformer = TransformerFactory.newInstance().newTransformer();
			xformer.transform(source, result);
		} catch (TransformerFactoryConfigurationError | TransformerException e) {
			e.printStackTrace();
		}
	}

	private static Document convertStringToDocument(String xmlStr) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
		DocumentBuilder builder;  
		try  
		{  
			builder = factory.newDocumentBuilder();  
			Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
			return doc;
		} catch (Exception e) {  
			e.printStackTrace();  
		} 
		return null;
	}

	private static String getBIRTXml(String[] colName,String title,int uId){
		return "<body>"+
				"<extended-item extensionName=\"Chart\" id=\""+uId+"\">"+
				"<xml-property name=\"xmlRepresentation\"><![CDATA[<ActuateChart version=\"2.6.1\">"+
				"<ChartWithAxes version=\"2.6.1\" type=\"Bar Chart\" subType=\"Side-by-side\" reverseCategory=\"false\">"+
				"<block>"+
				"<children _type=\"TitleBlock\" anchor=\"0\">"+
				"<outline>"+
				"<color _nil=\"1\"/>"+
				"</outline>"+
				"<label>"+
				"<caption value=\""+title+"\"/>"+
				"<background _nil=\"1\"/>"+
				"<outline>"+
				"<color _nil=\"1\"/>"+
				"</outline>"+
				"</label>"+
				"</children>"+
				"<children _type=\"Plot\">"+
				"<outline>"+
				"<color _nil=\"1\"/>"+
				"</outline>"+
				"<clientArea>"+
				"<outline>"+
				"<color _nil=\"1\"/>"+
				"</outline>"+
				"</clientArea>"+
				"</children>"+
				"<children _type=\"Legend\">"+
				"<outline>"+
				"<color _nil=\"1\"/>"+
				"</outline>"+
				"<clientArea>"+
				"<outline>"+
				"<color _nil=\"1\"/>"+
				"</outline>"+
				"</clientArea>"+
				"<separator>"+
				"<color _nil=\"1\"/>"+
				"</separator>"+
				"<title visible=\"true\">"+
				"<background _nil=\"1\"/>"+
				"<outline>"+
				"<color _nil=\"1\"/>"+
				"</outline>"+
				"</title>"+
				"</children>"+
				"<bounds _body=\"0.0,0.0,572.25,286.125\"/>"+
				"<outline>"+
				"<color _nil=\"1\"/>"+
				"</outline>"+
				"</block>"+
				"<extendedProperties name=\"enable.area.alt\" value=\"false\"/>"+
				"<extendedProperties name=\"enable.drill.category\" value=\"true\"/>"+
				"<extendedProperties name=\"enable.drill.series\" value=\"true\"/>"+
				"<sampleData>"+
				"<baseSampleData dataSetRepresentation=\"A, B, C\"/>"+
				"<orthogonalSampleData dataSetRepresentation=\"5,4,12\" seriesDefinitionIndex=\"0\"/>"+
				"</sampleData>"+
				"<emptyMessage>"+
				"<caption value=\"This chart contains no data.\"/>"+
				"<background _body=\",64,127,127,127\"/>"+
				"<outline>"+
				"<color _body=\",128,127,127,127\"/>"+
				"</outline>"+
				"</emptyMessage>"+
				"<axes categoryAxis=\"false\">"+
				"<title visible=\"true\">"+
				"<caption value=\""+colName[0]+"\"/>"+
				"<background _nil=\"1\"/>"+
				"<outline>"+
				"<color _nil=\"1\"/>"+
				"</outline>"+
				"</title>"+
				"<associatedAxes primaryAxis=\"true\">"+
				"<title visible=\"true\">"+
				"<caption value=\""+colName[1]+"\"/>"+
				"<background _nil=\"1\"/>"+
				"<outline>"+
				"<color _nil=\"1\"/>"+
				"</outline>"+
				"</title>"+
				"<seriesDefinitions>"+
				"<query definition=\"\"/>"+
				"<series _type=\"BarSeries\" seriesIdentifier=\""+colName[0]+"\" riser=\"0\">"+
				"<label>"+
				"<background _nil=\"1\"/>"+
				"<outline>"+
				"<color _nil=\"1\"/>"+
				"</outline>"+
				"</label>"+
				"<dataDefinition definition=\"row[&quot;"+colName[1]+"&quot;]\">"+
				"<grouping/>"+
				"</dataDefinition>"+
				"<triggers condition=\"6\">"+
				"<action type=\"1\">"+
				"<value _type=\"TooltipValue\" text=\"\" delay=\"200\"/>"+
				"</action>"+
				"</triggers>"+
				"<curveFitting _nil=\"1\"/>"+
				"</series>"+
				"<grouping _nil=\"1\"/>"+
				"</seriesDefinitions>"+
				"<lineAttributes>"+
				"<color _nil=\"1\"/>"+
				"</lineAttributes>"+
				"<label>"+
				"<background _nil=\"1\"/>"+
				"<outline>"+
				"<color _nil=\"1\"/>"+
				"</outline>"+
				"</label>"+
				"<majorGrid>"+
				"<lineAttributes>"+
				"<color _nil=\"1\"/>"+
				"</lineAttributes>"+
				"<tickAttributes>"+
				"<color _nil=\"1\"/>"+
				"</tickAttributes>"+
				"</majorGrid>"+
				"<minorGrid>"+
				"<lineAttributes>"+
				"<color _nil=\"1\"/>"+
				"</lineAttributes>"+
				"<tickAttributes>"+
				"<color _nil=\"1\"/>"+
				"</tickAttributes>"+
				"</minorGrid>"+
				"<origin>"+
				"<value _nil=\"1\"/>"+
				"</origin>"+
				"</associatedAxes>"+
				"<seriesDefinitions>"+
				"<query definition=\"\"/>"+
				"<series seriesIdentifier=\"\">"+
				"<label>"+
				"<background _nil=\"1\"/>"+
				"<outline>"+
				"<color _nil=\"1\"/>"+
				"</outline>"+
				"</label>"+
				"<dataDefinition definition=\"row[&quot;"+colName[0]+"&quot;]\"/>"+
				"<curveFitting _nil=\"1\"/>"+
				"</series>"+
				"<grouping enabled=\"true\"/>"+
				"</seriesDefinitions>"+
				"<lineAttributes>"+
				"<color _nil=\"1\"/>"+
				"</lineAttributes>"+
				"<label>"+
				"<caption>"+
				"<font rotation=\"45.0\"/>"+
				"</caption>"+
				"<background _nil=\"1\"/>"+
				"<outline>"+
				"<color _nil=\"1\"/>"+
				"</outline>"+
				"</label>"+
				"<majorGrid tickStyle=\"3\">"+
				"<lineAttributes>"+
				"<color _nil=\"1\"/>"+
				"</lineAttributes>"+
				"<tickAttributes visible=\"false\">"+
				"<color _nil=\"1\"/>"+
				"</tickAttributes>"+
				"</majorGrid>"+
				"<minorGrid>"+
				"<lineAttributes>"+
				"<color _nil=\"1\"/>"+
				"</lineAttributes>"+
				"<tickAttributes visible=\"false\">"+
				"<color _nil=\"1\"/>"+
				"</tickAttributes>"+
				"</minorGrid>"+
				"<origin>"+
				"<value _nil=\"1\"/>"+
				"</origin>"+
				"</axes>"+
				"<rotation>"+
				"<angles/>"+
				"</rotation>"+
				"</ChartWithAxes>"+
				"</ActuateChart>"+
				"]]></xml-property>"+
				"<property name=\"outputFormat\">JS</property>"+
				"<property name=\"inheritColumns\">true</property>"+
				"<property name=\"height\">286.125pt</property>"+
				"<property name=\"width\">572.25pt</property>"+
				"<property name=\"dataSet\">Data Set</property>"+
				"<list-property name=\"boundDataColumns\">"+
				" <structure>"+
				"<property name=\"name\">"+colName[0]+"</property>"+
				"<text-property name=\"displayName\"></text-property>"+
				"<expression name=\"expression\" type=\"javascript\">dataSetRow[\""+colName[0]+"\"]</expression>"+
				"<property name=\"dataType\">string</property>"+
				"</structure>"+
				"<structure>"+
				"<property name=\"name\">"+colName[1]+"</property>"+
				"<text-property name=\"displayName\">"+colName[1]+"</text-property>"+
				"<expression name=\"expression\" type=\"javascript\">dataSetRow[\""+colName[1]+"\"]</expression>"+
				"<property name=\"dataType\">string</property>"+
				"</structure>"+
				"</list-property>"+
				"</extended-item>"+
				"</body>";
	}
}
