package corposign.e2grau;

public class Equacao2Grau {
    public double a, b, c;
    public String r1, r2;
    public Double x1, x2;
    public String resposta;

    public Equacao2Grau(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
        this.r1 = "";
        this.r2 = "";
        this.resposta = "";
    }

    public Equacao2Grau(){
        this(0,0,0);
    }

    public boolean todosInteiros(){
        int a1 = (int) a;
        int b1 = (int) b;
        int c1 = (int) c;

        if((a1 - a) == 0){
            if((b1-b) == 0){
                if((c1-c) == 0) return true;
            }
        }
        return false;
    }

    public String exibeEquacao(){
        String aux = this.a + "x^2";
        if(this.b < 0) aux += " - " + Math.abs(this.b) + "x";
        else aux += " + " + this.b + "x";

        if(this.c < 0) aux += " - " + Math.abs(this.c);
        else aux += " + " + this.c;

        return aux + " = 0";
    }

    public Double valor(Double x){
        return a*x*x + b*x + c;
    }

    public String tresCasas(double n){
        int mil = ((int) Math.round(n*1000));
        return "" + (double) mil/1000;
    }

    public void resolve(){
        if(this.todosInteiros()) this.resolveInteiro();
        else this.resolveDouble();
    }

    public void resolveDouble(){
        resposta += "A equação digitada é " + "\\(" + this.exibeEquacao() + "\\)" + "\n";

        double delta = b*b - 4*a*c;

        resposta += "$$ \\Delta = b^2 - 4ac $$";
        resposta += "$$ \\Delta = (" + b + ")^2 - 4.(" + a + ").(" + c + ")$$";
        resposta += "$$ \\Delta = " + this.tresCasas(delta) + "$$";

        if(delta >= 0){
            if(delta > 0) resposta += "Como \\( \\Delta > 0 \\), há duas raízes reais e DISTINTAS, calculadas por: ";
            else resposta += "Como \\( \\Delta = 0 \\), há duas raízes reais e IGUAIS, calculadas por: ";
        }else resposta += "Como \\( \\Delta < 0 \\), não há raízes reais, no entanto há raízes complexas, calculadas por: ";

        resposta += "$$x = \\frac{-b \\pm \\sqrt{b^2-4ac}}{2a}$$\n";
        resposta += "$$ x = \\frac{-(" +this.b + ") \\pm \\sqrt{" + delta + "}}{2 \\times " + this.a + "}$$";

            if(delta == 0){
                x1 = -b/(2*a);
                this.r1 += "$$x1 = " + x1 + "$$";
                this.r2 = this.r1;
            }
            else{
                if(delta > 0){
                    x1 = (-b-Math.sqrt(delta))/(2*a);
                    x2 = (-b+Math.sqrt(delta))/(2*a);
                    this.r1 += "$$x_1 = " + this.tresCasas(x1) + "$$";
                    this.r2 += "$$x_2 = " + this.tresCasas(x2) + "$$";
                }
                else{
                    delta *= -1;

                    this.r1 += "$$x1 = " + this.tresCasas((-b/(2*a))) + "-" + this.tresCasas(Math.sqrt(delta)) + "i$$";
                    this.r1 += "$$x2 = " + this.tresCasas((-b/(2*a))) + "+" + this.tresCasas(Math.sqrt(delta)) + "i$$";

                }
        }

        resposta += this.r1 + "\n";
        resposta += this.r2 + "\n";
    }

    public void resolveInteiro(){
        Radical rad = new Radical(1,1);
        Fracao f1,f2;

        int a1 = (int) a;
        int b1 = (int) b;
        int c1 = (int) c;

        resposta += "A equação digitada é " + "\\(" + this.exibeEquacao() + "\\)" + " e apresenta coeficientes inteiros\n";

        rad.dentro = b1*b1 - 4*a1*c1;

        resposta += "$$ \\Delta = b^2 - 4ac $$";
        resposta += "$$ \\Delta = (" + b1 + ")^2 - 4.(" + a1 + ").(" + c1 + ")$$";
        resposta += "$$ \\Delta = " + rad.dentro + "$$";

        if(rad.dentro >= 0){
            if(rad.dentro > 0) resposta += "Como \\( \\Delta > 0 \\), há duas raízes reais e DISTINTAS, calculadas por: ";
            else resposta += "Como \\( \\Delta = 0 \\), há duas raízes reais e IGUAIS, calculadas por: ";
        }else resposta += "Como \\( \\Delta < 0 \\), não há raízes reais, no entanto há raízes complexas, calculadas por: ";

        resposta += "$$x = \\frac{-b \\pm \\sqrt{b^2-4ac}}{2a} = \\frac{-(" +this.b + ") \\pm \\sqrt{" + rad.dentro + "}}{2 \\times " + this.a + "}$$";

        rad.simplificaRadical();
        f1 = new Fracao(-b1,2*a1);
        f2 = new Fracao(rad.fora,2*a1);
        f2.simplificaFracao();

        if(rad.dentro == 1){
            f1.num = -b1 + rad.fora;
            f1.simplificaFracao();
            this.r1 += "$$x_1 = " + f1.exibeFracaoLatex() + "$$";
            f1.num = -b1 - rad.fora;
            f1.den = 2*a1;
            f1.simplificaFracao();
            this.r2 += "$$x_2 = " + f1.exibeFracaoLatex() + "$$";
            this.x1 = f1.valor() - f2.valor();
            this.x2 = f1.valor() + f2.valor();
        }
        else{
            if(rad.dentro == 0){
                f1.simplificaFracao();
                this.r1 += "$$x1 = " + f1.exibeFracaoLatex() + "$$";
                this.r2 = this.r1;
                this.x1 = f1.valor();
                this.x2 = f1.valor();
            }
            else{
                if(rad.dentro > 0){
                    f1.simplificaFracao();
                    this.r1 += "$$x_1 = " + f1.exibeFracaoLatex() + "+" + f2.exibeFracaoLatex() + "\\sqrt{" + rad.dentro + "}$$";
                    this.r2 += "$$x_2 = " + f1.exibeFracaoLatex() + "-" + f2.exibeFracaoLatex() + "\\sqrt{" + rad.dentro + "}$$";
                    this.x1 = f1.valor() - f2.valor()*Math.sqrt(rad.dentro);
                    this.x2 = f1.valor() + f2.valor()*Math.sqrt(rad.dentro);
                }
                else{
                    rad.dentro *= -1;
                    rad.simplificaRadical();
                    f2.num = rad.fora;
                    f2.den = 2*a1;
                    f2.simplificaFracao();
                    f1.simplificaFracao();
                    if(rad.dentro != 1){
                        this.r1 += "$$x_1 = " + f1.exibeFracaoLatex() + "+" + f2.exibeFracaoLatex() + "i*\\sqrt{" + rad.dentro + "}$$";
                        this.r2 += "$$x_2 = " + f1.exibeFracaoLatex() + "-" + f2.exibeFracaoLatex() + "i*\\sqrt{" + rad.dentro + "}$$";
                    }
                    else{
                        this.r1 += "$$x_1 = " + f1.exibeFracaoLatex() + "+" + f2.exibeFracaoLatex() + "i$$";
                        this.r2 += "$$x_2 = " + f1.exibeFracaoLatex() + "-" + f2.exibeFracaoLatex() + "i$$";
                    }
                }
            }
        }

        resposta += this.r1 + "\n";
        resposta += this.r2 + "\n";

    }

}