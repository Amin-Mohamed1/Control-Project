package org.example.backend.Services;

public class RouthArray {
    private String status ;
    private double[][] res ;
    public RouthArray(){
        status = "";
    }
    public RouthArray(double[][]res , String status){
        this.status = status;
        this.res = res ;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double[][] getRes() {
        return res;
    }

    public void setRes(double[][] res) {
        this.res = res;
    }

    private  boolean allSameSign(double[][] routh) {
        if (routh.length < 2) return true;
        double sign = Math.signum(routh[0][0]);
        for (int i = 1 ; i < routh.length ; ++i) {
            if (Math.signum(routh[i][0]) == -1*sign) {
                if(!status.equals("Unstable System due to duplicate of root on j-axis"))
                    status += " unstable due to change in sign of first column";
                return false;
            }
        }
        if(!status.equals("Unstable System due to duplicate of root on j-axis") && !status.equals("critically unstable due to entire zero row "))
            status += "stable due to unchanged sign of first column";
        return true;
    }
    private  boolean hasADifferentOrAbsentCoefficient(double[] coefficient ){
        double sign = Math.signum(coefficient[0]) ;
        if(sign < 1e-10){
            status += "unstable due to absence of coefficient \n";
            return false ;
        }
        int len = coefficient.length; ;
        for(int i = 1 ; i < len ; i++ ){
            double coefSign = Math.signum(coefficient[i]) ;
            if(coefSign != sign){
                status += "unstable due to change of signs  || absence of coefficient \n";
                return false ;
            }

        }
        return true ;
    }
    private  double[][] computeRouthArray(double[] coefficients) {
        int n = coefficients.length;
        int m = (n + 1) / 2;
        double[][] routhArray = new double[n][m];
        int k = 0 ;
        boolean found = false;
        for (int i = 0; k < m; i += 2) {
            routhArray[0][k++] = coefficients[i];
        }
        k = 0;
        for (int i = 1; k < m && i < n; i += 2) {
            routhArray[1][k++] = coefficients[i];
        }
        for (int i = 2 ; i < n ; ++i) {
            double b = routhArray[i - 1][0];
            double d = routhArray[i - 2][0];
            for (int j = 0; j < m - 1; j++) {
                double a = routhArray[i - 1][j + 1];
                double c = routhArray[i - 2][j + 1];
                if (b == 0) {
                    b = 1e-10; // Avoid division by zero
                }
                routhArray[i][j] = (c * b - d * a) / b;
            }

            if(Math.abs(routhArray[i][0]) <= 1e-10 && checkEntireZeroRow(routhArray , i)){
                if(!changeRowByDerivative(routhArray , i)){
                    found = true;
                }
            }
        }
        if(found)
            this.status = "Unstable System due to duplicate of root on j-axis";
        return routhArray;
    }
    public  RouthArray isSystemStable(double[] coefficients) {
        int n = coefficients.length;
        if(!hasADifferentOrAbsentCoefficient(coefficients) )
            return new RouthArray(new double[n][(n+1)/2] , this.status) ;

        double[][] routhArray = computeRouthArray(coefficients);
        allSameSign(routhArray) ;
        return new RouthArray(routhArray , this.status);
    }
    private boolean checkEntireZeroRow(double[][] routh , int row){
        for(int i = 1 ; i < routh[0].length ; ++i){
            if(routh[row][i] > 1e-10){
                return false ;
            }
        }
        this.status = "critically unstable due to entire zero row ";
        return true;
    }
    private boolean changeRowByDerivative(double[][] routh , int row){
        int aboveRow = row - 1 ;
        int degreeOfAux = (routh.length - 1) - aboveRow ;
        for(int i = 0 ; i < routh[0].length && degreeOfAux >= 0  ; i++ , degreeOfAux -=2 ){
            routh[row][i] = routh[aboveRow][i] * degreeOfAux ;
        }
        if(( aboveRow < routh.length-3 ) ){
            if(2*Math.sqrt(routh[aboveRow][0] * routh[aboveRow][2]) - Math.abs(routh[aboveRow][1]) < 1e-10){
                this.status = "Unstable System due to duplicate of root on j-axis";
                System.out.println(this.status);
                return false ;
            }

        }
        return true ;
    }
    public  void print(double[][] routhArray){
        for(double[] x : routhArray){
            for(double y : x)
                System.out.print(y+" ");
            System.out.println();
        }
        System.out.println();
    }

}
