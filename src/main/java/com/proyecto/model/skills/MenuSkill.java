package com.proyecto.model.skills;

public class MenuSkill {

    public static Skill create(String skillName) {
        switch (skillName) {
            case "Golpe Rapido":      return new AtaqueSkill("Golpe Rapido", "Ataque fisico basico.", 1.0);
            case "Embestida":         return new AtaqueSkill("Embestida", "Ataque fuerte con multiplicador.", 1.5);
            case "Colmillo Ardiente": return new AtaqueSkill("Colmillo Ardiente", "Mordisco de fuego.", 1.3);
            case "Garra Helada":      return new AtaqueSkill("Garra Helada", "Zarpazo helado.", 1.2);
            case "Golpe Critico":     return new CriticoSkill("Golpe Critico", "Ignora defensa, daño alto.", 2.0);
            case "Rayo Letal":        return new CriticoSkill("Rayo Letal", "Descarga electrica, ignora defensa.", 2.3);
            case "Vendaval":          return new CriticoSkill("Vendaval", "Torbellino de viento.", 1.8);
            case "Curacion":          return new CurarSkill("Curacion", "Recupera 30 HP.", 30);
            case "Regeneracion":      return new CurarSkill("Regeneracion", "Recupera 50 HP.", 50);
            case "Concentracion":     return new CurarSkill("Concentracion", "Sube el ataque 10 puntos.", 10);
            case "Furia":             return new BuffSkill("Furia", "Sube el ataque 20 puntos.", 20);
            case "Corrosion":         return new DebuffSkill("Corrosion", "Baja la defensa del rival 8 puntos.", 8);
            case "Rugido":            return new DebuffSkill("Rugido", "Baja la defensa del rival 12 puntos.", 12);
            default:                  return new AtaqueSkill(skillName, "Ataque basico.", 1.0);
        }
    }
}
