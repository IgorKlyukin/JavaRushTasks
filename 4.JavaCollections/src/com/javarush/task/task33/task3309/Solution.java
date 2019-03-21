package com.javarush.task.task33.task3309;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException, ParserConfigurationException, TransformerException {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setCoalescing(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();


            marshaller.marshal(obj, document);

            NodeList nodeList = document.getElementsByTagName(tagName);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node n = nodeList.item(i);
                n.getParentNode().insertBefore(document.createComment(comment), n);
            }

            addCDATA(document, document.getDocumentElement());

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");


            StringWriter write = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(write));
            return write.toString();
    }

    public static void addCDATA(Document doc, Node startNode) {

        for (Node node = startNode.getFirstChild(); node != null;) {
            Node nextNode = node.getNextSibling();
            String nodeValue = node.getNodeValue();
            if (nodeValue != null && node.getNodeType() == Node.TEXT_NODE && nodeValue.matches(".*[<>&'\"].*")) {
                CDATASection cdataSection = doc.createCDATASection(nodeValue);
//                node.getParentNode().insertBefore(cdataSection, node.getNextSibling());
//                node.getParentNode().removeChild(node);
                node.getParentNode().replaceChild(cdataSection, node);
            }

            if (node.hasChildNodes()) {
                addCDATA(doc, node);
            }

            node = nextNode;
        }
    }

    public static void main(String[] args) throws JAXBException, ParserConfigurationException, TransformerException {
        System.out.println(Solution.toXmlWithComment(new First(), "second", "it's a comment"));
    }

    @XmlRootElement(name = "first")
    public static class First {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
        @XmlElement(name = "second")
        public String item3 = "";
        @XmlElement(name = "third")
        public String item4;
        @XmlElement(name = "forth")
        public Second[] third = new Second[]{new Second()};
        @XmlElement(name = "fifth")
        public String item5 = "need CDATA because of \"";
    }

    public static class Second {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
    }
}
