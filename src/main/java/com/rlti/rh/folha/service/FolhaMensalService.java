package com.rlti.rh.folha.service;

import com.rlti.rh.folha.application.api.FolhaMensaRequest;
import com.rlti.rh.folha.application.api.FolhaMensalResponse;

public interface FolhaMensalService {
    FolhaMensalResponse newFolha(FolhaMensaRequest folhaMensaRequest);
}
