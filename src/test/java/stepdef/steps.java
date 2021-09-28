package stepdef;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class steps {
	JsonPath js;
	String inforesponse;
	String inforesponse1;
	@Given("user is on marketcap page")
	public void user_is_on_marketcap_page() throws Throwable{
		RestAssured.baseURI="https://pro-api.coinmarketcap.com";
		String response=	given().queryParam("symbol", "BTC,USDT,ETH").header("X-CMC_PRO_API_KEY","aba65e4a-d3f1-4da8-90d7-f213b2d77045").when().get("v1/cryptocurrency/map").then().log().all().assertThat().statusCode(200).extract().response().getBody().asString();

		

			}

	

	@When("user hits cryptocurrencymap  call to get id of {string} and {string} and {string}")
	public void user_hits_cryptocurrencymap_call_to_get_id_of_and_and(String string, String string2, String string3) throws Throwable {

	String response=	given().queryParam("symbol", "BTC,USDT,ETH").header("X-CMC_PRO_API_KEY","aba65e4a-d3f1-4da8-90d7-f213b2d77045").when().get("v1/cryptocurrency/map").then().log().all().assertThat().statusCode(200).extract().response().getBody().asString();

System.out.println(response);
 js= new JsonPath(response);
System.out.println("bbb");
	}

	@Then("user and convert them into {string} using price-conversion")
	public void user_and_convert_them_into_using_price_conversion(String string)throws Throwable {
		int count=	js.getInt("data.size()");
		System.out.println(count);
		for(int i =0; i<count;i++) {
			String s=js.get("data["+i+"].symbol");
			if(s.equals("BTC")||s.equals("ETH")||s.equals("USDT")) {
				System.out.println("true");
				
				int id =js.getInt("data["+i+"].id");
				System.out.println(id);
				
				String responseofconversion=	given().queryParam("id",id).queryParam("amount", "10").queryParam("convert","BOB").header("X-CMC_PRO_API_KEY","aba65e4a-d3f1-4da8-90d7-f213b2d77045").when().get("v1/tools/price-conversion").then().log().all().assertThat().statusCode(200).extract().response().getBody().asString();
				JsonPath	js1=new JsonPath(responseofconversion);
				float ff= js1.getFloat("data.quote.BOB.price");
				if(ff!=0) {
				
			   System.out.println("converted");
				}		
		}
	}

		System.out.println("babc");
}
	@When("user hits cryptocurrencyinfo  call to get info of ETH")
	public void user_hits_cryptocurrencyinfo_call_to_get_info_of_eth() {
		 inforesponse=	given().queryParam("id","1027").header("X-CMC_PRO_API_KEY","aba65e4a-d3f1-4da8-90d7-f213b2d77045").when().get("v1/cryptocurrency/info").then().log().all().assertThat().statusCode(200).extract().response().getBody().asString();
		
		//System.out.println(inforesponse);
		
		 
	}

	@Then("validate the logo, technicaldoc,symbol,date, tag")
	public void validate_the_logo_technicaldoc_symbol_date_tag() {
		JsonPath js2= new JsonPath(inforesponse);
		String url=js2.get("data.1027.logo");
		String techdoc=js2.get("data.1027.urls.technical_doc[0]");
		System.out.println(techdoc);
		String symbol=js2.get("data.1027.symbol");
		System.out.println(symbol);
		String date=js2.get("data.1027.date_added");
		System.out.println(date);
		String tag=js2.get("data.1027.tags[0]");
		System.out.println(tag);
		String expectedurl="https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png.";
		String expectedtechdoc="https://github.com/thereum/wiki/wiki/White-Paper";
		String expectedsymbol="ETH";
		String expecteddate="2015-08-07T00:00:00.000Z";
		String expectedtag="mineable";
		if(url.equals(expectedurl)&&techdoc.equals(expectedtechdoc)&&symbol.equals(expectedsymbol)&&date.equals(expecteddate)&&tag.equals(expectedtag)) {
			System.out.println("all parameteres are correct");
		} 
	}
	@When("user hits cryptocurrencyinfo  call to get info of first ten")
	public void user_hits_cryptocurrencyinfo_call_to_get_info_of_first_ten() {
		 inforesponse1=	given().queryParam("id","1,2,3,4,5,6,7,8,9,10").header("X-CMC_PRO_API_KEY","aba65e4a-d3f1-4da8-90d7-f213b2d77045").when().get("v1/cryptocurrency/info").then().log().all().assertThat().statusCode(200).extract().response().getBody().asString();
		 
	}

	@Then("validate the tag and correct symbol associated with them")
	public void validate_the_tag_and_correct_symbol_associated_with_them() {
		JsonPath js3= new JsonPath(inforesponse1);
		for(int i=1;i<=10;i++)
		{
		String tags=js3.get("data."+i+".tags[0]");
		System.out.println(tags);
		
		String crypto=js3.get("data."+i+".slug");
		System.out.println(crypto);
	}


	}
}
