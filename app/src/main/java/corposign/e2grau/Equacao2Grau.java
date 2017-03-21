package corposign.e2grau;

public class Equacao2Grau {
    public int a, b, c;
    public String r1, r2;
    public Double x1, x2;
    public String resposta;

    public Equacao2Grau(int a, int b, int c){
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

    public void resolve(){
        Radical rad = new Radical(1,1);
        Fracao f1,f2;

        resposta += "A equação digitada é " + "\\(" + this.exibeEquacao() + "\\)" + "\n";

        rad.dentro = b*b - 4*a*c;

        resposta += "$$ \\Delta = b^2 - 4ac $$";
        resposta += "$$ \\Delta = (" + this.b + ")^2 - 4.(" + this.a + ").(" + this.c + ")$$";
        resposta += "$$ \\Delta = " + rad.dentro + "$$";

        if(rad.dentro >= 0){
            if(rad.dentro > 0) resposta += "Como \\( \\Delta > 0 \\), há duas raízes reais e DISTINTAS, calculadas por: ";
            else resposta += "Como \\( \\Delta = 0 \\), há duas raízes reais e IGUAIS, calculadas por: ";
        }else resposta += "Como \\( \\Delta < 0 \\), não há raízes reais, no entanto há raízes complexas, calculadas por: ";

        resposta += "$$x = \\frac{-b \\pm \\sqrt{b^2-4ac}}{2a} = \\frac{-(" +this.b + ") \\pm \\sqrt{" + rad.dentro + "}}{2 \\times " + this.a + "}$$";

        rad.simplificaRadical();
        f1 = new Fracao(-b,2*a);
        f2 = new Fracao(rad.fora,2*a);
        f2.simplificaFracao();

        if(rad.dentro == 1){
            f1.num = -b + rad.fora;
            f1.simplificaFracao();
            this.r1 += "$$x_1 = " + f1.exibeFracaoLatex() + "$$";
            f1.num = -b - rad.fora;
            f1.den = 2*a;
            f1.simplificaFracao();
            this.r2 += "$$x_2 = " + f1.exibeFracaoLatex() + "$$";
            this.x1 = f1.valor() + f2.valor();
            this.x2 = f1.valor() - f2.valor();
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
                    this.x1 = f1.valor() + f2.valor()*Math.sqrt(rad.dentro);
                    this.x2 = f1.valor() - f2.valor()*Math.sqrt(rad.dentro);
                }
                else{
                    rad.dentro *= -1;
                    rad.simplificaRadical();
                    f2.num = rad.fora;
                    f2.den = 2*a;
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