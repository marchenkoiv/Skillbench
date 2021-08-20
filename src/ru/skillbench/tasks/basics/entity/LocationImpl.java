package ru.skillbench.tasks.basics.entity;

public class LocationImpl implements Location{
    private String name;
    private Type type;
    private Location parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setParent(Location parent) {
        this.parent = parent;
    }

    public String getParentName() {
        if (parent != null) {
            return parent.getName();
        }
        return "--";
    }

    public Location getTopLocation() {
        Location m = this;
        if(parent != null){
            m = parent.getTopLocation();
        }
        return m;
    }

    public boolean isCorrect() {
        boolean m = true;
        if(parent != null ){
            if(parent.getType().ordinal() >= this.getType().ordinal()){
                return false;
            }
            m = parent.isCorrect();
        }
        return m;
    }

    public String getAddress() {
        String s = "";
        if (this.getName().endsWith(".") || ((this.getName().indexOf(".") < this.getName().indexOf(" ")) && this.getName().contains(".") && this.getName().contains(" "))){
            s = this.getName();
        }else {
            s = this.getType().getNameForAddress()+this.getName();
        }
        if(parent != null){
            s += ", "+ parent.getAddress();
        }
        return s;
    }

    @Override
    public String toString(){
        return name + " (" + type.toString() + ")";
    }

    public static  void main(String[] args){
        Location l = new LocationImpl();
        Location l1 = new LocationImpl();
        l.setName("Moscow");
        l.setType(Type.CITY);
        l.setParent(l1);
        l1.setName("Rus.");
        l1.setType(Type.REGION);
        System.out.println(l.getAddress());
        System.out.println(l.toString());
        System.out.println(l.isCorrect());
    }
}
