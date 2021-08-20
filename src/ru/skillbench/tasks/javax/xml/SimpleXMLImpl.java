package ru.skillbench.tasks.javax.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class SimpleXMLImpl implements SimpleXML{
    public String createXML(String tagName, String textNode) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement(tagName);
            document.appendChild(root);
            root.appendChild(document.createTextNode(textNode));
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(root);
            StringWriter writer = new StringWriter();
            StreamResult res = new StreamResult(writer);
            transformer.transform(domSource, res);
            String ress = writer.toString();
            //System.out.println(ress.substring(38));
            return ress.substring(38);
            } catch (TransformerException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        return null;
    }

    public String parseRootElement(InputStream xmlStream) throws SAXException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            DefaultHandler handler = new SaxHandler();
            parser.parse(xmlStream, handler);
            return ((SaxHandler) handler).getRoot();
            //return
        } catch (ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class SaxHandler extends DefaultHandler {
        private String root = null;
        public void startDocument() throws SAXException {
        }
        public void endDocument() throws SAXException {
        }
        public void startElement(String uri, String localName,
                                 String qName, Attributes attributes)
                throws SAXException {
            if(root == null){ root = qName;}
            System.out.println(qName);
        }
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
        }
        public void characters(char ch[], int start, int length)
                throws SAXException {
        }
        public void ignorableWhitespace(char ch[], int start, int length)
                throws SAXException {
        }
        public String getRoot(){
            return root;
        }
    }

    public static  void main(String[] args) {
    SimpleXML a = new SimpleXMLImpl();
    a.createXML("root", "<super&tag>");
        try {
            System.out.println(a.parseRootElement(new FileInputStream(new File(".\\xmlfile.xml"))));
        } catch (SAXException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
