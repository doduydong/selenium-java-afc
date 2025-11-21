package javaBasic;

public class J_11_Strings {

    public static void main(String[] args) {
        String project = "Selenium Java";

        System.out.println(project.length());
        System.out.println(project.charAt(10));

        System.out.println(project.startsWith("Se"));
        System.out.println(project.endsWith("va"));
        System.out.println(project.contains("m J"));

        String jdk = project.substring(9);
        String jdkVersion = "11";

        System.out.println(jdk.concat(jdkVersion));
        System.out.println(jdk + " " + jdkVersion);

        System.out.println(jdk.toUpperCase().equals("JAVA"));
        System.out.println(jdk.toLowerCase().equals("java"));
        System.out.println(jdk.equalsIgnoreCase("JAVA"));

        String info = "iPhone 16e starts at just $599";

        String[] infoArray = info.split(" ");
        String price = infoArray[infoArray.length - 1].replace("$", "");

        double priced = Double.parseDouble(price);
        System.out.println(priced == 599.0);

        price = String.valueOf(priced);
        System.out.println(price.equals("599.0"));

        String value = "\t Hello World! \n";
        System.out.println(value.trim());

        value = " ";
        System.out.println(value.isBlank());
        System.out.println(value.isEmpty());

        String template = "I'm a/an %s.";
        String dynamicValue = "SDET";
        System.out.println(String.format(template, dynamicValue));
    }

}
