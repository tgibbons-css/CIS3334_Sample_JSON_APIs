package css.sampleAPIs;

//https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/usd/eur.json
//  {"date": "2022-03-09","eur": 0.91749 }

public class CurrencyRate2 {
    public String date;
    //public double eur;
    public Float eur;

    public Float getEUR() {
        return eur;
    }

    @Override
    public String toString() {
        return "CurrencyRate = " + eur;
    }

}
