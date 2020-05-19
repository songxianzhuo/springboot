package com.example.securitydemo;

import com.example.securitydemo.util.RSAUtils;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

@SpringBootTest
class SecuritydemoApplicationTests {

	private final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCL0W3aQ-ZHu2cg4o3ri-69jnMlptoPw9wdpJW0j4jYwj9cxRi-5_XlbT7N03x1LbbjQHMR-G4-ROCrDXQaSifuOywioCBWOBW41a33IYv24V8vJdrnJe40ibkescjCOXes8mTeJH2B7A4piKKt4YBW_QYwTCsAyLIV4MOvuUwFfQIDAQAB";

	private final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIvRbdpD5ke7ZyDijeuL7r2OcyWm2g_D3B2klbSPiNjCP1zFGL7n9eVtPs3TfHUttuNAcxH4bj5E4KsNdBpKJ-47LCKgIFY4FbjVrfchi_bhXy8l2ucl7jSJuR6xyMI5d6zyZN4kfYHsDimIoq3hgFb9BjBMKwDIshXgw6-5TAV9AgMBAAECgYEAgO3ZP6jXI5Ri78caGXeK6g7-jVzDvj_crRUE22B0T3Un2pMBU6EwdaR8gm4fWSqTeBEJSyvr9M2s4u7f_hIdqMpgFr-BxjT-Ahm1GxGJpTtcx4xBs7x8ufZNCfGj1P7Kr1PbP2zMqUn1DFvq2L_GA4Xvp1SApXqzEpm4GgHrUkECQQDGpUEq5JCa9_OnRo1SCgDG49yR5j_QRv8MA4Jl5nn32VkyhnXHbUsax4WtfWosQtvQFiMouHHZq1JbOtZV4SYNAkEAtC_8MZOatz1IPA1B52EYLbarsDWZ_GAfAXgM8Je-1bA2IN95Vl2etILsMAGj7I1Hi9Rp9YqANjhb070H0whxMQJACS8ewjLrFJ8eHr--TOq1enySeBRjIugFZrLK1pc_UFw2KYXZ8Cx3aneLEKhZvk4hB4_edEWCzgODvUANJM9SCQJAFUtKcY1xDlCmtboXXMHDurtIxPVAsmulnmXSmahFPDmr2-ytCUDdHZYPVYkMF0uG9gZvUz4_8qhsabSANTddMQJARjacLOe7C2kmN7X1ZmyNXlH-nZr-iRkiL4CQVY2v3SyEH9zXBPT3z3CuPZFXXHQ_A3FffQ0qMDEKdYOefoGmjw";

	/**
	 * 签发人
	 */
	private final String issure = "server";
	/**
	 * 主题
	 */
	private final String subject = "token";
	/**
	 * 受众
	 */
	private final String audience = "client";


	@Test
	void contextLoads() {
	}

	/**
	 * 使用JJWT的java库创建jwt
	 */
	@Test
	public void createJwtTest() throws InvalidKeySpecException, NoSuchAlgorithmException {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// token 1分钟后过期
		calendar.add(Calendar.MINUTE,5);
		// 设置JWT Claims
		JwtBuilder builder= Jwts.builder()
				.setHeaderParam("typ","JWT")
				.setId(UUID.randomUUID().toString()) // jwt的唯一ID
				.setIssuer(issure) // 签发人
				.setSubject(subject) // 主题
				.setAudience(audience) // 受众
				.setIssuedAt(date) // 签发时间
				.setExpiration(calendar.getTime()) // 过期时间
				//.signWith(SignatureAlgorithm.HS256,"xiaosong"); // 使用HMAC算法进行签名，私钥自定义
				.signWith(SignatureAlgorithm.RS256, RSAUtils.getPrivateKey(privateKey));// 使用RSA算法进行签名，密钥必须使用私钥，否则会报错
		Map<String,Object> map = new HashMap<>();
		map.put("name","songxianzhuo");
		map.put("userId","10000");
		// 设置自定义信息
		map.forEach(builder::claim);
		// 构建JWT并将其序列化为紧凑的URL安全字符串
		String token = builder.compact();
		System.out.println("token：" + token);
	}

	/**
	 * jwt验证
	 * 注释事项：
	 * jwt过期会报io.jsonwebtoken.ExpiredJwtException异常。
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void verifyJwtTest() throws InvalidKeySpecException, NoSuchAlgorithmException {
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJqdGkiOiJmMmQ1YzRlZS1kZWVmLTQ4NGUtYTg0MC02YTJmZWFmMzA4NzIiLCJpc3MiOiJzZXJ2ZXIiLCJzdWIiOiJ0b2tlbiIsImF1ZCI6ImNsaWVudCIsImlhdCI6MTU4OTE3NDM4MiwiZXhwIjoxNTg5MTc0NjgyLCJuYW1lIjoic29uZ3hpYW56aHVvIiwidXNlcklkIjoiMTAwMDAifQ.IAYs4FJVttcvrDBf7uePPaAWCUX6A38gHUjhsRbIdiVmPzWUBIx0Msv--GZm0HqN-XDNzM9YQKPrx536dyoSFKmtMUEAT-IrJwq3-JnhQyxKyIknO5gZ_aWX72DVoXRFXPGGoYSkDmxNf_rkTS7hGQVjlu3FulpwzzGXtBbakb8";
		Jwt jwt = Jwts.parser()
				//.setSigningKey("xiaosong") // HMAC算法解析
				.setSigningKey(RSAUtils.getPublicKey(publicKey)) // RSA公钥进行解密
				.requireAudience(audience)
				.requireSubject(subject)
				.requireIssuer(issure)
				.parseClaimsJws(token);
		Claims claims = (Claims)jwt.getBody();
		Header header = jwt.getHeader();
		System.out.println(claims);
		System.out.println(header);
	}

}
