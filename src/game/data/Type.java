package game.data;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

public class Type {
    private final String name;
    private final Map<String, Double> resistances;

    private Type(String name, Map<String, Double> resistances) {
        this.name = name;
        this.resistances = resistances;
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getResistances() {
        return resistances;
    }

    public static Type get(String name) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("Types.xml"));
            doc.getDocumentElement().normalize();

            NodeList typeList = doc.getElementsByTagName("type");

            for (int i = 0; i < typeList.getLength(); i++) {
                Element type = (Element) typeList.item(i);
                
                if (type.getAttribute("name").equals(name)) {
                    Map<String, Double> resistances = new HashMap<>();

                    NamedNodeMap attributes = type.getAttributes();
                    for (int j = 0; j < attributes.getLength(); j++) {
                        Node attribute = attributes.item(j);
                        String attrName = attribute.getNodeName();
                        if (!attrName.equals("name")) { 
                            double value = Double.parseDouble(attribute.getNodeValue());
                            resistances.put(attrName, value);
                        }
                    }
                    return new Type(name, resistances);
                }
            }

            System.err.println("Type with name " + name + " not found.");
            return null;

        } catch (Exception e) {
            System.err.println("Error loading types from XML: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}