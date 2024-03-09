package PropertyUtility;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

public class PropertyFile
{
    public FileInputStream fileInputStream;
    public Properties properties;

    //in constructor incarcam fisierul de la o anumita locatie
    public PropertyFile(String path)
    {
        loadFile(path);
    }

    //metoda care incarca fisierul
    public void loadFile(String path)
    {
        properties = new Properties();
        //get path by RMBC on DriverResorce.properties -> CopyPath
        //build the path in a generic way.
        try
        {
            fileInputStream = new FileInputStream("src/test/resources/" + path + ".properties");
            properties.load(fileInputStream);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //metoda care returneaza o valoare pe baza unei chei
    public String getValue(String key)
    {
        return properties.getProperty(key);
    }

    //metoda care returneaza toate valorile si cheile dintr-un fisier
    public HashMap<String, String> getAllValues()
    {
        HashMap<String, String> values = new HashMap<>();

        for (Object key: properties.keySet())
        {
            values.put(key.toString(), properties.getProperty(key.toString()));
        }
        return values;
    }
}
