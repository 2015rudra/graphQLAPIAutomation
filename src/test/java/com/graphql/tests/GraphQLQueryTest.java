package com.graphql.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.qa.pojos.GraphQLQuery;
import com.qa.pojos.QueryVariable;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;


public class GraphQLQueryTest {
	
	@Test
	@Ignore
	public void getAllFilmsTest() {
		
		//https://swapi-graphql.netlify.app/.netlify/functions/index
		
		File schema = new File("src\\resources\\AllFilms_Schema.json");
		
		RestAssured.baseURI ="https://swapi-graphql.netlify.app";
		String query = "{\"query\":\"{\\n  allFilms {\\n    films {\\n      title\\n    }\\n  }\\n}\\n\",\"variables\":null}";
		
		given().log().all()
			.contentType("application/json")
			.body(query)
				.when().log().all()
					.post("/.netlify/functions/index")
						.then().log().all()	
							.assertThat()
								.statusCode(200)
									.and()
										.body("data.allFilms.films[0].title", equalTo("A New Hope"))
											.and()
											.body(JsonSchemaValidator.matchesJsonSchema(schema));
					
	}
	
	
	
	@Test
	@Ignore
	public void getAllUsersTest() {
		RestAssured.baseURI ="https://hasura.io";
		String query = "{\"query\":\"{\\n  users(limit: 10) {\\n    id\\n    name\\n  }\\n}\\n\",\"variables\":null}";
		
		given().log().all()
			.contentType("application/json")
			.header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYwYzYyMzlhNGQ1OTVkMDA2Nzg0NjEzYyJ9LCJuaWNrbmFtZSI6Im5hdmVlbmFuaW1hdGlvbjIwIiwibmFtZSI6Im5hdmVlbmFuaW1hdGlvbjIwQGdtYWlsLmNvbSIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci82MTVjNTVlNjBiZTU2N2ZmMDRiZTBjYTUwMmM5ZWExMz9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRm5hLnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIxLTA2LTIyVDA3OjAzOjMzLjgyMFoiLCJpc3MiOiJodHRwczovL2dyYXBocWwtdHV0b3JpYWxzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2MGM2MjM5YTRkNTk1ZDAwNjc4NDYxM2MiLCJhdWQiOiJQMzhxbkZvMWxGQVFKcnprdW4tLXdFenFsalZOR2NXVyIsImlhdCI6MTYyNDM0NTQxNiwiZXhwIjoxNjI0MzgxNDE2LCJhdF9oYXNoIjoiTENVX1Izc3ZyNXF1ZXFBajJMTXMzQSIsIm5vbmNlIjoiSUdoRHNycDEubG5zMTFQOFlCTHRkMTFKYXR3UVRPZnIifQ.B4ipspXwxiNAP7phKGiUQnhZa_0BPgP53sk9Pbm7trUmoxNhvcp1lJB1mq90mRHmXLZNR-6iy3oqjCgIlKTuywpM__ncdx_VAbDtyjgMJyxU6N5VTClxJrN8Hb_q5sZ3i7q322OGRxdYCM_lNivu0HBwyRrtu09DXp_QFHIwJp6gt6k28dulSut7KBmms_sJKxJAsI6T2b-UBcMwaXTnJXB-j7d-ofubwfX8ZNwo5GgOtUPtEeV8yq9mjjs2t55ceIjLA9J9xzvSDFB3ACphvp0ST9lDoWCDGJZGyC0Jl4ubTnjeXKHjUGRilj1fLbJx1PlPAskkoWhCkpmLsE_4XQ")
				
				.body(query)
					.when().log().all()
						.post("/learn/graphql")
							.then().log().all()
								.assertThat()
									.statusCode(200)
										.body("data.users[0].name", equalTo("tui.glen"));	
			
	}
	
	
	@DataProvider
	public Object[][] getQueryData() {
		return new Object[][] {{"10"},{"6"}};
	}
	
	@Test(dataProvider = "getQueryData")
	@Ignore
	public void getAllUsersTestWithDataTest(String limit) {
		RestAssured.baseURI ="https://hasura.io";
		String query = "{\"query\":\"{\\n  users(limit: "+limit+") {\\n    id\\n  }\\n}\\n\",\"variables\":null}";
		
		given().log().all()
			.contentType("application/json")
			.header("Authorization", "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDY0YTkzNzA5OTllNTA5YjkwNmQyZmZmOSJ9LCJuaWNrbmFtZSI6InJ1cGVzaC5rYWRhbTE5MTIiLCJuYW1lIjoicnVwZXNoLmthZGFtMTkxMkBnbWFpbC5jb20iLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvZWJhZGZjNmY5YjBlN2ZlNTdlZjYwOGVhNGIwMjc5NmY_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZydS5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMy0wOC0xOVQwODoxMDo1MS4zNTNaIiwiaXNzIjoiaHR0cHM6Ly9ncmFwaHFsLXR1dG9yaWFscy5hdXRoMC5jb20vIiwiYXVkIjoiUDM4cW5GbzFsRkFRSnJ6a3VuLS13RXpxbGpWTkdjV1ciLCJpYXQiOjE2OTI0MzI2NTQsImV4cCI6MTY5MjQ2ODY1NCwic3ViIjoiYXV0aDB8NjRhOTM3MDk5OWU1MDliOTA2ZDJmZmY5IiwiYXRfaGFzaCI6ImJxWWdoeDRqM0V4NEVrRDc5U05sanciLCJzaWQiOiIwdm9MaFFfaGlCc0lKNlIzY2FnQ0t0azYxX1BvdjVqeSIsIm5vbmNlIjoifm42NVZUWFpub1k0M0NBMXpFSGJjUWpkUE51ejZ0U28ifQ.ja1jet7OLAqNBtiAWBHZBe5IMhkOcKejazvBZ8LDwMVChuMBUa06ghPJcZHt1oOLsuNue1pldIqXFsh0rUMTGlg50kL-kB_4C_NqUEQfopL_gjQLtydDKnoytBcuGbtFhUXp7In-dKvD-vVnDXkdvp_8pt0tcz4WOC0-YLTrKk6-kjDCPNAGZTJTZlm5CQ5HcJFvU0Bv0G12NhicIa6NahUeT_myKPa6MHezSEgD6_oePJ6nRD3qVo6f8cJEtE4QcRfwXJgrwXDv7DSgLuRecxKaKnKN6RA73XVVsSfk9XdRJtvQ42pOpGTlJgawXhvcHwPT6jVA9m5h0l1zi3ixEA")
				
				.body(query)
					.when().log().all()
						.post("/learn/graphql")
							.then().log().all()
								.assertThat()
									.statusCode(200);
			
	}
	
	
	@Test
	//@Ignore
	public void getAllUsers_WithPojoTest() {
		RestAssured.baseURI ="https://hasura.io";
		GraphQLQuery query = new GraphQLQuery();
		
		query.setQuery("query ($limit: Int!, $name:String!) {\n"
				+ "  users(limit: $limit, where: {name: {_eq: $name}}) {\n"
				+ "    id\n"
				+ "    name\n"
				+ "  }\n"
				+ "}");
		
		QueryVariable variable = new QueryVariable();
		variable.setLimit(5);
		variable.setName("tui.glen");
		
		query.setVariables(variable);
		
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDY0YTkzNzA5OTllNTA5YjkwNmQyZmZmOSJ9LCJuaWNrbmFtZSI6InJ1cGVzaC5rYWRhbTE5MTIiLCJuYW1lIjoicnVwZXNoLmthZGFtMTkxMkBnbWFpbC5jb20iLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvZWJhZGZjNmY5YjBlN2ZlNTdlZjYwOGVhNGIwMjc5NmY_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZydS5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMy0wOC0xOVQwODoxMDo1MS4zNTNaIiwiaXNzIjoiaHR0cHM6Ly9ncmFwaHFsLXR1dG9yaWFscy5hdXRoMC5jb20vIiwiYXVkIjoiUDM4cW5GbzFsRkFRSnJ6a3VuLS13RXpxbGpWTkdjV1ciLCJpYXQiOjE2OTI0MzI2NTQsImV4cCI6MTY5MjQ2ODY1NCwic3ViIjoiYXV0aDB8NjRhOTM3MDk5OWU1MDliOTA2ZDJmZmY5IiwiYXRfaGFzaCI6ImJxWWdoeDRqM0V4NEVrRDc5U05sanciLCJzaWQiOiIwdm9MaFFfaGlCc0lKNlIzY2FnQ0t0azYxX1BvdjVqeSIsIm5vbmNlIjoifm42NVZUWFpub1k0M0NBMXpFSGJjUWpkUE51ejZ0U28ifQ.ja1jet7OLAqNBtiAWBHZBe5IMhkOcKejazvBZ8LDwMVChuMBUa06ghPJcZHt1oOLsuNue1pldIqXFsh0rUMTGlg50kL-kB_4C_NqUEQfopL_gjQLtydDKnoytBcuGbtFhUXp7In-dKvD-vVnDXkdvp_8pt0tcz4WOC0-YLTrKk6-kjDCPNAGZTJTZlm5CQ5HcJFvU0Bv0G12NhicIa6NahUeT_myKPa6MHezSEgD6_oePJ6nRD3qVo6f8cJEtE4QcRfwXJgrwXDv7DSgLuRecxKaKnKN6RA73XVVsSfk9XdRJtvQ42pOpGTlJgawXhvcHwPT6jVA9m5h0l1zi3ixEA")
			.body(query)
		.when().log().all()
			.post("/learn/graphql")
		.then().log().all()
			.assertThat()
				.statusCode(200)
					.and()
						.body("data.users[0].name", equalTo("tui.glen"));
	
	
	
	}
	
	

}
