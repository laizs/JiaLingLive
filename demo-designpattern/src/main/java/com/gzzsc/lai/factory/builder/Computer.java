package com.gzzsc.lai.factory.builder;

/**
 * @className Computer
 * @Deacription 建筑者模式
 * @Author laizs
 * @Date 2021/8/20 14:49
 **/
public class Computer {
    private String cpu;
    private String screen;
    private String memory;
    private String mainboard;
    private Computer(){

    }
    private Computer(Computer.Builder builder){
        this.cpu=builder.cpu;
        this.screen=builder.screen;
        this.memory=builder.memory;
        this.mainboard=builder.mainboard;
    }
    public String getCpu() {
        return cpu;
    }

    public String getScreen() {
        return screen;
    }

    public String getMemory() {
        return memory;
    }

    public String getMainboard() {
        return mainboard;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", screen='" + screen + '\'' +
                ", memory='" + memory + '\'' +
                ", mainboard='" + mainboard + '\'' +
                '}';
    }

    /**
     * builder
     */
    public static class Builder {
        private String cpu;
        private String screen;
        private String memory;
        private String mainboard;
        //链式方法
        public Builder cpu(String cpu){
            this.cpu=cpu;
            return this;
        }
        public Builder screen(String screen){
            this.screen=screen;
            return this;
        }
        public Builder memory(String memory){
            this.memory=memory;
            return this;
        }
        public Builder mainboard(String mainboard){
            this.mainboard=mainboard;
            return this;
        }
        /**
         * 建造
         * @return
         */
        public Computer build(){
            return new Computer(this);
        }
    }
}

