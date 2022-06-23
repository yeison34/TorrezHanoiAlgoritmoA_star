
public class NodoEstado {
    private int g;
    private int h;
    private int fh;
    private NodoEstado padre;
    private String nombre;
    private Pila a,b,c;
    Sucesores sucesores;
    
    public NodoEstado(int g,int h,int fh,String nombre,NodoEstado padre){
        
        this.padre=padre;
        this.h=h;
        this.g=g;
        this.fh=fh;
        this.nombre=nombre;
        sucesores=new Sucesores();
        a=new Pila("A");
        b=new Pila("B");
        c=new Pila("C");
    }
    
    public NodoEstado(){
        
    }
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setFh(int fh){
        this.fh=fh;
    }
   
    public void setG(int g){
        this.g=g;
    }
    
    public void setH(int h){
        this.h=h;
    }
    
    public int getFh(){
        return fh;
    }
   
    public int getG(){
        return g;
    }
    
    public int getH(){
        return h;
    }
    
    public NodoEstado getPadre(){
        return padre;
    }
    
    public NodoEstado raiz(){
        return this;
    }
    
    public Pila getPilaA(){
        return a;
    }
    
    public void setPilaA(Pila a){
        this.a=a;
    }
    
    public Pila getPilaB(){
        return b;
    }
    
    public void setPilaB(Pila b){
        this.b=b;
    }
    
    public Pila getPilaC(){
        return c;
    }
    
    public void setPilaC(Pila c){
        this.c=c;
    }
    
    
}
