/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 *
 * @author Red
 */
public class StringInsertBuilder {
    
    public static String TYPE_STRING    = "String";
    public static String TYPE_DOUBLE    = "Double";
    public static String TYPE_INTEGER   = "Integer";
    
    private LinkedHashMap<String, String>  columns;
    private HashMap<String, Double>  doubles;
    private HashMap<String, String>  strings;
    private HashMap<String, Integer> integers;
    
    /*UTILISATION
        StringInsertBuilder request = new StringInsertBuilder();
        request.addColumns("name",          "");//string
        request.addColumns("categoryId",    1);//int
        request.addColumns("protein",       1.0);//double
        request.addColumns("lipid",         1.0);
        request.addColumns("carbohydrate",  1.0);
        String query = request.build("Food");
    */
    
    public StringInsertBuilder() {
        columns  = new LinkedHashMap<>();
        doubles  = new HashMap<>();
        strings  = new HashMap<>();
        integers = new HashMap<>();
    }
    
    private String add(String name, String type) {
        String id = (type+columns.size());
        columns.put(id, name.replace("'", "\\'"));
        return id;
    }
    
    public void addColumns(String name, double value) {
        String id = add(name, TYPE_DOUBLE);
        doubles.put(id, value);
    }
    
    public void addColumns(String name, String value) {
        String id = add(name, TYPE_STRING);
        strings.put(id, value.replace("'", "\\'"));
    }
    
    public void addColumns(String name, int value) {
        String id = add(name, TYPE_INTEGER);
        integers.put(id, value);
    }
    
    public String build(String tableName) {
        String param  = "";
        String select = "";
        String where  = "";
        Set<String> keys = columns.keySet();
        for(String k:keys) {
            //NOM DE LA COLONNE
            if(param.equals("")) {
                param   += ""+columns.get(k);
            }
            else {
                param   += ", "+columns.get(k);
            }
            //VALEUR DE LA COLONNE
            if (k.contains(TYPE_INTEGER)) {
                if(select.equals("")) {
                    select  += ""+integers.get(k);
                }
                else {
                    select  += ", "+integers.get(k);
                }
                if (!where.equals("")) { where += " AND"; }
                where   += " t."+columns.get(k)+"="+integers.get(k);
            } else if(k.contains(TYPE_DOUBLE)) {
                if(select.equals("")) {
                    select  += ""+doubles.get(k);
                }
                else {
                    select  += ", "+doubles.get(k);
                }
                if (!where.equals("")) { where += " AND"; }
                where   += " t."+columns.get(k)+"="+doubles.get(k);
            } else if(k.contains(TYPE_STRING)) {
                if(select.equals("")) {
                    select  += ""+strings.get(k);
                }
                else {
                    select  += ", '"+strings.get(k)+"'";
                }
                if (!where.equals("")) { where += " AND"; }
                where   += " t."+columns.get(k)+"='"+strings.get(k)+"'";
            }
        }

        String query = "INSERT INTO "+tableName+" ("+param+") \n" +
        "SELECT "+select+" FROM DUAL \n" +
        "WHERE NOT EXISTS (\n\tSELECT * FROM "+tableName+" t";

        if(!where.equals("")) {
            query += " WHERE"+where;
        }
        query += "\n);";
        return query;
    }
}
