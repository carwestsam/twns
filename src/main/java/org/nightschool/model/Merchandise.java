package org.nightschool.model;

import java.util.ArrayList;

/**
 * Created by carwest on 15-1-8.
 */
public class Merchandise {
    private int _id;
    private String _name;
    private String _desc;
    private String _imgUrl;
    private double _price;
    public Merchandise(){};
    public Merchandise( int id, String name, String desc, String imgUrl, double price ){
        _id = id;
        _name = name;
        _desc = desc;
        _imgUrl = imgUrl;
        _price = price;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_desc() {
        return _desc;
    }

    public void set_desc(String _desc) {
        this._desc = _desc;
    }

    public String get_imgUrl() {
        return _imgUrl;
    }

    public void set_imgUrl(String _imgUrl) {
        this._imgUrl = _imgUrl;
    }

    public double get_price() {
        return _price;
    }

    public void set_price(double _price) {
        this._price = _price;
    }
}
