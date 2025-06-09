package entrega4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class ClasePrincipal {
    private Map<String, List<Animal>>clasificar;
    private List<Animal>animales;
    
 public  ClasePrincipal(){
        this.clasificar=new HashMap<>();
        this.animales = new ArrayList<>();
        
    }