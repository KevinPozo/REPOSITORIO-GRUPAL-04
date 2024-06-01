/**
 * 
 * @author KevinPozo
 * Title: Inversión de Dependencia y Responsabilidad Única.
 * 
 * 
 * */
package Interfaces;

public interface ILife extends IDrawable, IMovable{
    int getMaxHealth(); 
    int getCurrentHealth(); 
    void decreaseHealth(int amount); 
    void increaseHealth(int amount); 
    void setMaxHealth(int maxHealth); 
    void setCurrentHealth(int currentHealth); 
}

