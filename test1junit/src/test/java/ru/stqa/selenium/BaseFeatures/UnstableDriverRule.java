package ru.stqa.selenium.BaseFeatures;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;


public class UnstableDriverRule implements TestRule {

    private WebDriverRule driverRule;

    public UnstableDriverRule(WebDriverRule driverRule) {
        this.driverRule = driverRule;
    }

    @Override
    public Statement apply(Statement base, Description desc) {
        return new UnstableDriverStatement(base, desc);
    }

    public class UnstableDriverStatement extends Statement {

        private Statement base;
        private Description desc;

        public UnstableDriverStatement(Statement base, Description desc) {
            this.base = base;
            this.desc = desc;
        }

        @Override
        public void evaluate() throws Throwable {

            Unstable unstb = desc.getAnnotation(Unstable.class);

            if (unstb != null) {

                int nreps = unstb.value();
                int repCounter = 0;
                boolean success = false;

                while(!success && repCounter < nreps) {
                    try {
                        ++repCounter;
                        success = true;
                        base.evaluate();
                        System.out.println("Test success");
                    } catch (Throwable ex) {
                        System.out.println("Test failed");
                        success = false;
                    }
                }

            } else {
                base.evaluate();
            }

        }

    }

}
