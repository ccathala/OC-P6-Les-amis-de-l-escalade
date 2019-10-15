package com.charles.lesamisdelescalade.consumer.dto;

import java.util.List;

import com.charles.lesamisdelescalade.model.dto.AccountPageData;

public interface AccountPageDataDao {

	List<AccountPageData> getDataForAccountPageDataBySiteId(int departementId);

}
