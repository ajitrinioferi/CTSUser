/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afa.foreveryuser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADI
 */
public class Transaction {

    private String scheduleid;
    private List<String> seat = new ArrayList<String>();
    private int qty;
    private int cardnumber;
    private int totalprice;
    private int transid;
    private boolean ticketstatus;

    public String getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(String scheduleid) {
        this.scheduleid = scheduleid;
    }

    public List<String> getSeat() {
        return seat;
    }

    public void setSeat(List<String> seat) {
        this.seat = seat;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(int cardnumber) {
        this.cardnumber = cardnumber;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public int getTransid() {
        return transid;
    }

    public void setTransid(int transid) {
        this.transid = transid;
    }

    public boolean getTicketstatus() {
        return ticketstatus;
    }

    public void setTicketstatus(boolean ticketstatus) {
        this.ticketstatus = ticketstatus;
    }
}
