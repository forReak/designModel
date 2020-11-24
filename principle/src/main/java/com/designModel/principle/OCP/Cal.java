package com.designModel.principle.OCP;

import java.math.BigDecimal;

/**
 * @author furao
 * @desc 根据流量计算费用
 * @date 2020/11/24
 * @package com.designModel.principle.OCP
 */
public class Cal {
    private int flow;

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public Cal(int flow) {
        this.flow = flow;
    }

    public BigDecimal sum(){
        return new BigDecimal(flow * 10);
    }
}
