package ar.com.blox.bloxsys.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CUITValidator implements ConstraintValidator<CUIT, Long> {

    private static final String coef = "5432765432"; //coeficiente

    @Override
    public void initialize(CUIT aCUIT) {
    }

    @Override
    public boolean isValid(Long cuit, ConstraintValidatorContext context) {
        if (cuit == null) {
            return true;
        }
        String cuitStr = String.valueOf(cuit);
        try {
            int su = 0;
            int lCuit = cuitStr.length();
            if (lCuit != 11) {
                return false;
            }
            for (int i = 1; i < 11; i++) {
                String Cd1 = coef.substring(i - 1, i);
                String Cd2 = cuitStr.substring(i - 1, i);
                int cf = Integer.parseInt(Cd1); //casteo...
                int ct = Integer.parseInt(Cd2); //casteo...
                su += (cf * ct);
            }
            int md = su / 11;
            int re = su - (md * 11);
            if (re > 1) {
                re = 11 - re;
            }
            String CdDv = cuitStr.substring(lCuit - 1, lCuit);
            int dv = Integer.parseInt(CdDv); //casteo...
            return dv == re;

        } catch (NumberFormatException e) {
            return false;
        }

    }
}
