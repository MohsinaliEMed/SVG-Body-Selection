package com.bodychart;

/**
 * @Author: Sunasara Mohsinali
 * @Date: 14-Oct-22
 */
public class BodyChartInfo {
    private String bodyPartName;
    private boolean isSelected;
    private String numberOfPain;

    public String getBodyPartName() {
        return bodyPartName;
    }

    public void setBodyPartName(String bodyPartName) {
        this.bodyPartName = bodyPartName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getNumberOfPain() {
        return numberOfPain;
    }

    public void setNumberOfPain(String numberOfPain) {
        this.numberOfPain = numberOfPain;
    }
}
