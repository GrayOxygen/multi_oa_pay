package com.huiyang.wang.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import com.huiyang.wang.model.PayOrder;

public interface PayOrderMapper {
	/**
	 * 
	 * @author 王辉阳
	 * @date 2017年6月29日 上午12:07:05
	 * @Description 实现动态sql
	 */
	public class DynaSqlProvider {
		public String update(final PayOrder order) {
			return new SQL() {
				{
					UPDATE("pay_order");
					if (order.getId() != null) {
						SET("id = #{id}");
					}
					if (order.getAppId() != null) {
						SET("appId = #{appId}");
					}
					if (order.getMchId() != null) {
						SET("mchId = #{mchId}");
					}

					if (order.getSubAppId() != null) {
						SET("subAppId = #{subAppId}");
					}
					if (order.getSubMchId() != null) {
						SET("subMchId = #{subMchId}");
					}

					if (order.getDeviceInfo() != null) {
						SET("deviceInfo = #{deviceInfo}");
					}
					if (order.getNonceStr() != null) {
						SET("nonceStr = #{nonceStr}");
					}
					if (order.getBody() != null) {
						SET("body = #{body}");
					}
					if (order.getDetail() != null) {
						SET("detail = #{detail}");
					}
					if (order.getAttach() != null) {
						SET("attach = #{attach}");
					}
					if (order.getTotalFee() != null) {
						SET("totalFee = #{totalFee}");
					}
					if (order.getSpBillCreateIp() != null) {
						SET("spBillCreateIp = #{spBillCreateIp}");
					}
					if (order.getTimeStart() != null) {
						SET("timeStart = #{timeStart}");
					}
					if (order.getTimeExpire() != null) {
						SET("timeExpire = #{timeExpire}");
					}
					if (order.getGoodsTag() != null) {
						SET("goodsTag = #{goodsTag}");
					}
					if (order.getNotifyUrl() != null) {
						SET("notifyUrl = #{notifyUrl}");
					}
					if (order.getTradeType() != null) {
						SET("tradeType = #{tradeType}");
					}
					if (order.getProductId() != null) {
						SET("productId = #{productId}");
					}
					if (order.getOpenId() != null) {
						SET("openId = #{openId}");
					}
					if (order.getSubOpenId() != null) {
						SET("subOpenId = #{subOpenId}");
					}
					if (order.getPrepayId() != null) {
						SET("prepayId = #{prepayId}");
					}
					if (order.getCodeUrl() != null) {
						SET("codeUrl = #{codeUrl}");
					}
					if (order.getSubscribed() != null) {
						SET("subscribed = #{subscribed}");
					}
					if (order.getSubscribed() != null) {
						SET("subSubscribed = #{subSubscribed}");
					}
					if (order.getBankType() != null) {
						SET("bankType = #{bankType}");
					}
					if (order.getFeeType() != null) {
						SET("feeType = #{feeType}");
					}
					if (order.getCashFee() != null) {
						SET("cashFee = #{cashFee}");
					}
					if (order.getCashFeeType() != null) {
						SET("cashFeeType = #{cashFeeType}");
					}
					if (order.getCouponFee() != null) {
						SET("couponFee = #{couponFee}");
					}
					if (order.getCouponCount() != null) {
						SET("couponCount = #{couponCount}");
					}
					if (order.getTransactionId() != null) {
						SET("transactionId = #{transactionId}");
					}
					if (order.getTimeEnd() != null) {
						SET("timeEnd = #{timeEnd}");
					}
					if (order.getTradeState() != null) {
						SET("tradeState = #{tradeState}");
					}
					if (order.getStatus() != null) {
						SET("status = #{status}");
					}
					WHERE("id = #{id}");
				}
			}.toString();
		}
	}

	@Select("SELECT * FROM pay_order WHERE id = #{id}")
	PayOrder get(@Param("id") String orderId);

	@Select("SELECT * FROM pay_order WHERE id = #{id} FOR UPDATE")
	PayOrder getForUpdate(@Param("id") String id);

	@UpdateProvider(type = DynaSqlProvider.class, method = "update")
	void update(PayOrder order);

	@Delete("delete from pay_order where id = #{id}")
	void delete(@Param("id") String id);

	@Insert("INSERT INTO pay_order(id,appId,mchId,subAppId,subMchId,deviceInfo,nonceStr,body,detail,attach,feeType,"//
			+ "totalFee,spBillCreateIp,timeStart,timeExpire,goodsTag,notifyUrl,tradeType,productId,openId,subOpenId,status,"//
			+ "tradeState,ctime,mtime) VALUE(#{id},#{appId},#{mchId},#{subAppId},#{subMchId},#{deviceInfo},#{nonceStr},"//
			+ "#{body},#{detail},#{attach},#{feeType},#{totalFee},#{spBillCreateIp},#{timeStart},#{timeExpire},#{goodsTag},"//
			+ "#{notifyUrl},#{tradeType},#{productId},#{openId},#{subOpenId},#{status},#{tradeState},#{ctime},#{mtime})") //
	void save(PayOrder order);

	@Select("<script>" + //
			" SELECT COUNT(1) FROM pay_order" + //
			" <where>" + //
			"<if test='startTime != null\'>" + //
			"    AND pay_order.ctime >= #{startTime}" + //
			" </if>" + //
			" <if test='endTime != null'>" + //
			"     AND pay_order.cTime &lt;= #{endTime}" + //
			" </if>" + //
			" <if test='companyId != null'>" + //
			"     AND pay_order.companyId = #{companyId}" + //
			" </if>" + //
			" <if test='shopId != null'>" + //
			"     AND pay_order.shopId  = #{shopId}" + //
			" </if>" + //
			" <if test='cashierId != null'>" + //
			"   AND pay_order.cashierId = #{cashierId}" + //
			"</if>" + //
			"<if test='status != null'>" + //
			"   AND pay_order.status = #{status}" + //
			"</if>" + //
			" <if test='id_in != null'>" + //
			"AND pay_order.id IN" + //
			"<foreach collection='id_in' index='index' item='item' open='(' separator=',' close=')'>" + //
			"	#{item}" + //
			"</foreach>" + //
			"</if>" + //
			"</where>" + //
			"</script>")
	Long count(Map<String, Object> map);// id_in存放id的字符串list

}
