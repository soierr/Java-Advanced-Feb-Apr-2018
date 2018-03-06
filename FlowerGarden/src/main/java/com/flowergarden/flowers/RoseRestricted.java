package com.flowergarden.flowers;

import javax.xml.bind.annotation.XmlRootElement;

import com.flowergarden.flowers.restricted.RestrictedByColor;
import com.flowergarden.flowers.restricted.FlowerRestrictedColor;
import com.flowergarden.flowers.restricted.FlowerRestrictedColor.COLORS;

@XmlRootElement
public class RoseRestricted extends Rose implements RestrictedByColor{
	

	/* (non-Javadoc)
	 * @see com.flowergarden.flowers.restricted.RestrictedByColor#getParameter()
	 */
	@Override
	public COLORS getParameter() {
		
		return FlowerRestrictedColor.COLORS.RED;
	}


}
