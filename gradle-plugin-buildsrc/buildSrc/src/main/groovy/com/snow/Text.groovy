package com.snow

import org.gradle.api.Plugin
import org.gradle.api.Project

class Text implements Plugin<Project>{
    @Override
    void apply(Project project) {
        project.register("text"){
            doLast{
                println("自定义text插件")
            }
        }
    }
}