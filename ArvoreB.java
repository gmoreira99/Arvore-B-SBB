public class ArvoreB {
    private static class Pagina {
        Item r[];
        Pagina p[];
        int n;

        public Pagina(int mm) {
            r = new Item[mm];
            p = new Pagina[mm+1];
            n = 0;
        }
    }

    private Pagina raiz;
    private int m, mm;
    private int count;

    public ArvoreB(int m) {
        raiz = null;
        this.m = m;
        mm = 2*m;
        count = 1;
    }

    public int getCount() {
        return count;
    }

    private void insereNaPagina (Pagina ap, Item reg, Pagina apDir) {
        int k = ap.n-1;
        while (k>=0 && reg.compara(ap.r[k])<0) {
            ap.r[k+1] = ap.r[k];
            ap.p[k+2] = ap.p[k+1];
            k--;
        }
        ap.r[k+1] = reg; ap.p[k+2] = apDir; ap.n++;     
    }

    private Pagina insere(Item reg, Pagina ap, Item[] regRetorno, boolean[] cresceu) {
        Pagina apRetorno = null;
        if (ap == null) {
            cresceu[0] = true;
            regRetorno[0] = reg;
        } else {
            int i = 0;
            while (i<ap.n-1 && reg.compara(ap.r[i])>0)
                i++;      
            if (reg.compara(ap.r[i])==0) {
                System.out.println("Erro: Registro ja existente");
                cresceu[0] = false;
            } else {
                if (reg.compara(ap.r[i]) > 0)
                    i++;
                apRetorno = insere(reg, ap.p[i], regRetorno, cresceu);
                if (cresceu[0])          
                    if (ap.n < this.mm) {   // Pagina tem espaco
                        this.insereNaPagina(ap, regRetorno[0], apRetorno);
                        cresceu[0] = false;
                        apRetorno = ap;
                    } else {    // Overflow -> Pagina tem que ser dividida
                        Pagina apTemp = new Pagina(this.mm);
                        apTemp.p[0] = null;
                        if (i <= this.m) {
                            this.insereNaPagina(apTemp, ap.r[this.mm-1], ap.p[this.mm]);
                            ap.n--;
                            this.insereNaPagina(ap, regRetorno[0], apRetorno);
                        } else
                            this.insereNaPagina (apTemp, regRetorno[0], apRetorno);
                        for (int j=this.m+1; j < this.mm; j++) {
                            this.insereNaPagina(apTemp, ap.r[j], ap.p[j+1]);
                            ap.p[j+1] = null;   // Transfere a posse da memoria
                        }
                        ap.n = this.m;
                        apTemp.p[0] = ap.p[this.m+1]; 
                        regRetorno[0] = ap.r[this.m];
                        apRetorno = apTemp;
                    }
            }      
        }
        return (cresceu[0] ? apRetorno : ap);
    }

    public void insere(Item reg) {
        Item regRetorno[] = new Item[1];
        boolean cresceu[] = new boolean[1];
        Pagina apRetorno = this.insere(reg, this.raiz, regRetorno, cresceu);
        if (cresceu[0]) {
            Pagina apTemp = new Pagina(this.mm);
            apTemp.r[0] = regRetorno[0];
            apTemp.p[0] = this.raiz;
            apTemp.p[1] = apRetorno;
            this.raiz = apTemp;
            this.raiz.n++;
        } else
            this.raiz = apRetorno;
    }

    public Item pesquisa(Item reg, Pagina ap) {
        if (ap == null) // Registro nao encontrado
            return null;
        else {
            int i = 0;
            while (i<ap.n-1 && reg.compara(ap.r[i])>0)
                i++;
            if (reg.compara(ap.r[i]) == 0) {
                return ap.r[i];
            } else if (reg.compara(ap.r[i]) < 0) {
                count++;
                return pesquisa(reg, ap.p[i]);
            } else {
                count++;
                return pesquisa(reg, ap.p[i+1]);
            }
        }
    }

    public Item pesquisa(Item reg) {
        return this.pesquisa(reg, this.raiz);
    }
}