package com.designModel.principle.OCP.userPackage;

import com.designModel.principle.OCP.Cal;

import java.math.BigDecimal;

/**
 * @author furao
 * @desc
 * @date 2020/11/24
 * @package com.designModel.principle.OCP
 */
public class Cal2 extends Cal {

    public Cal2(int sum) {
        super(sum);
    }

    @Override
    public BigDecimal sum(){
        return new BigDecimal( getFlow() * 50);
    }
}
