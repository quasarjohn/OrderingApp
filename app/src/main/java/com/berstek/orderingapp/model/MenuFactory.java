package com.berstek.orderingapp.model;

import java.util.ArrayList;

public class MenuFactory {

  private ArrayList<Menu> menus = new ArrayList<>();

  public MenuFactory() {


  }

  public ArrayList<Menu> getMenus() {

    Menu m = new Menu();
    m.setTitle("Tapsilogs");
    m.setDetails("tapa sinangag itlog");
    m.setPrice(105);
    m.setImg_url("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Tapsilog_in_saudi_arabia.jpg/250px-Tapsilog_in_saudi_arabia.jpg");
    m.setPriority(2);

    Menu m1 = new Menu();
    m1.setTitle("Longsilo");
    m1.setPrice(100);
    m1.setDetails("longanisa sinangag itlog");
    m1.setImg_url("http://www.iskandals.com/eats/wp-content/uploads/2008/06/iskandals-longsilog.jpg");
    m1.setPriority(4);

    Menu m2 = new Menu();
    m2.setTitle("Tocilog ");
    m2.setPrice(100);
    m2.setDetails("tocino sinangag itlog");
    m2.setImg_url("http://farm6.static.flickr.com/5062/5582260552_567303fb6c.jpg");
    m2.setPriority(3);

    Menu m3 = new Menu();
    m3.setTitle("Letsilog ");
    m3.setPrice(135);
    m3.setImg_url("http://panlasangpinoy.com/wp-content/uploads/2009/03/lechonkawali1.jpg");
    m3.setDetails("lechon kawali sinangag itlog");
    m3.setPriority(6);

    Menu m4 = new Menu();
    m4.setTitle("Liemposilog");
    m4.setImg_url("http://panlasangpinoy.com/wp-content/uploads/2017/06/How-to-Cook-Liempo-Sinangag-at-Itlog-Meal-266x160.jpg?x28997");
    m4.setPrice(135);
    m4.setDetails("liempo sinangag itlog");
    m4.setPriority(5);


    Menu m5 = new Menu();
    m5.setImg_url("https://pbs.twimg.com/media/CTtJMquWcAAI1KJ.jpg");
    m5.setTitle("Barsilog ");
    m5.setPrice(135);
    m5.setDetails("bbq sinangag itlog");
    m5.setPriority(7);


    Menu m6 = new Menu();
    m6.setImg_url("https://s3-media2.fl.yelpcdn.com/bphoto/gQ4JRfY753xixWmz0eVPeQ/o.jpg");
    m6.setTitle("Chicsilog");
    m6.setPrice(100);
    m6.setDetails("chitcharong bituka sinangag itlog");
    m6.setPriority(8);


    Menu m7 = new Menu();
    m7.setTitle("Crabsilog");
    m7.setPrice(125);
    m7.setPriority(9);
    m7.setImg_url("https://masarapdin.files.wordpress.com/2012/04/crab-in-oyster-sauce-1024x683.jpg");
    m7.setDetails("crabrelyeno sinangag itlog");

    Menu m8 = new Menu();
    m8.setTitle("Dinsilog");
    m8.setPrice(95);
    m8.setPriority(10);
    m8.setImg_url("https://scontent-sea1-1.cdninstagram.com/t51.2885-15/s480x480/e35/c0.134.1080.1080/21227401_1921373414791704_4889350873815187456_n.jpg?ig_cache_key=MTIyMjY0NDkyMDA1MjcxODcxOQ%3D%3D.2.c");
    m8.setDetails("dinuguan sinangag itlog");

    Menu m9 = new Menu();
    m9.setTitle("chicksilog ");
    m9.setPrice(150);
    m9.setImg_url("http://static.panoramio.com/photos/large/27175812.jpg");
    m9.setPriority(11);
    m9.setDetails("chicken sinangag itlog");

    Menu m10 = new Menu();
    m10.setTitle("Bangsilog");
    m10.setPrice(160);
    m10.setImg_url("http://balay.ph/wp-content/uploads/2017/01/silog4.jpg");
    m10.setPriority(0);
    m1.setImg_url("https://images.summitmedia-digital.com/spotph/images/files/10-must-try-all-day-breakfast-spots/1320832587-maxs-BonelessBangus.jpg");
    m10.setDetails("bangus sinangag itlog");

    Menu m11 = new Menu();
    m11.setTitle("Beef Bulalo");
    m11.setPrice(395);
    m11.setImg_url("https://scm-assets.constant.co/scm/unilever/e9dc924f238fa6cc29465942875fe8f0/323cd101-15ec-47a8-a10a-02c1cfc02b7d.jpg");
    m11.setDetails("");
    m11.setPriority(1);

    menus.add(m);
    menus.add(m1);
    menus.add(m2);
    menus.add(m3);
    menus.add(m4);
    menus.add(m5);
    menus.add(m6);
    menus.add(m7);
    menus.add(m8);
    menus.add(m9);
    menus.add(m10);
    menus.add(m11);

    return menus;
  }

  public ArrayList<Menu> getDrinks() {
    ArrayList<Menu> drinks = new ArrayList<>();

    Menu sprite = new Menu();
    sprite.setImg_url("https://cdn.shopify.com/s/files/1/0959/7176/products/sprite-logo_1024x1024.jpg?v=1454413466");
    sprite.setPriority(6);
    sprite.setTitle("Sprite");
    sprite.setPrice(20);

    Menu coke = new Menu();
    coke.setImg_url("http://www.coca-cola.com/content/dam/GO/CokeZone/Common/global/logo/logodesktop/coca-cola-logo-260x260.png");
    coke.setPriority(0);
    coke.setTitle("Coca Cola");
    coke.setPrice(15);

    Menu rc = new Menu();
    rc.setImg_url("https://upload.wikimedia.org/wikipedia/en/thumb/e/ed/RC_Cola_logo.svg/1024px-RC_Cola_logo.svg.png");
    rc.setTitle("RC Cola");
    rc.setPrice(18);
    rc.setPriority(4);

    Menu mdew = new Menu();
    mdew.setImg_url("https://vignette.wikia.nocookie.net/mountaindew/images/6/66/4x2.797_Mtn_Dew_logo.jpg/revision/latest?cb=20170623035114");
    mdew.setPriority(0);
    mdew.setTitle("M Dew");
    mdew.setPrice(20);

    Menu water = new Menu();
    water.setImg_url("");
    water.setPriority(2);
    water.setTitle("Water");
    water.setPrice(20);

    Menu sevenup = new Menu();
    sevenup.setImg_url("https://suntorypepsico.vn/files/upload/logo_7up.png");
    sevenup.setPriority(3);
    sevenup.setTitle("7-up");
    sevenup.setPrice(20);

    Menu pepsi = new Menu();
    pepsi.setImg_url("http://www.pepsicva.com/images/products/pepsi.jpg");
    pepsi.setPriority(1);
    pepsi.setTitle("Pepsi");
    pepsi.setPrice(20);

    drinks.add(sprite);
    drinks.add(coke);
    drinks.add(rc);
    drinks.add(mdew);
    drinks.add(sevenup);
    drinks.add(pepsi);

    return drinks;
  }
}
