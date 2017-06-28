package com.huiyang.wang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.huiyang.wang.model.OfficialAccount;

/**
 * 公众号Mapper
 * 
 */
public interface OfficialAccountMapper {
	@Select("SELECT * FROM official_account  WHERE appId = #{appId} ")
	OfficialAccount getByAppId(@Param("appId") String appId);

	@Select("SELECT * FROM official_account  WHERE id = #{id} ")
	OfficialAccount get(String id);

	@Update({ "<script>",
			"UPDATE official_account" + //
					"<set>" + //
					"<if test='deleted != null'>" + //
					"deleted = true" + //
					"</if>" + //
					"</set>" + //
					"WHERE id IN" + //
					"<foreach collection='list' index='index'  item='item' open='(' separator=',' close=')'>" + //
					"#{item}" + //
					"</foreach>", //
			"</script>" })
	void batchSoftOpt(@Param("deleted") boolean deleted,@Param("list")   List<String> list);

	@Update({ "<script>", //
			"UPDATE official_account " + //
					"<set> " + //
					"<if test='name != null'> " + //
					"		  name = #{name}, " + //
					"	</if> " + //
					"<if test='appId != null'> " + //
					"		  appId = #{appId}, " + //
					"	</if> " + //
					"<if test='secret != null'> " + //
					"		  secret = #{secret}, " + //
					"	</if> " + //
					"<if test='mchId != null'> " + //
					"		  mchId = #{mchId}, " + //
					"	</if> " + //
					"<if test='key != null'> " + //
					"		  `key` = #{key}, " + //
					"	</if> " + //
					"<if test='type != null'> " + //
					"		  `type` = #{type}, " + //
					"	</if> " + //
					"<if test='templateId != null'> " + //
					"		  templateId = #{templateId}, " + //
					"	</if> " + //
					"<if test='token != null'> " + //
					"		  token = #{token}, " + //
					"	</if> " + //
					" mtime = sysdate() " + //
					" </set> " + //
					"WHERE id = #{id} ", //
			"</script>" })
	void update(OfficialAccount oa);

	@Insert("INSERT INTO official_account(id,name,appId,secret,mchId,`key`,type,ctime,token,templateId) " //
			+ "VALUE (#{id},#{name},#{appId},#{secret},#{mchId},#{key},#{type},#{ctime},#{token},#{templateId}) ") // s
	void save(OfficialAccount module);

}
