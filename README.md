# WaterPower

WaterPower是一个基于Forge的模组，使用Forgelin为Forge提供Kotlin语言适配器，并捆绑Kotlin标准库为Kotlin语言开发提供环境。本模组为Minecraft添加了独特的发电系统、专用机械和材料。

(原作者为[huanghongxun](https://github.com/huanghongxun/WaterPower/commits?author=huanghongxun),由于原版本停留在1.12且纹理资源配置问题,在MC 1.12.2运行此MOD,物品栏内图标均出现显示缺失问题,故而Fork此项目进行原问题修复和内容更新,版本号从原有0.5.8的继承升级为0.5.9开始,本MOD的玩法参考原作者的[视频讲解](https://www.bilibili.com/video/BV1Gs411Z7sP))

原作者的HMCL项目非常出名,有需要使用的也可以去[这里](https://github.com/HMCL-dev/HMCL)

## 项目信息

- **Minecraft版本**: 1.12.2
- **Forge版本**: 14.23.5.2860
- **开发语言**: Kotlin 1.3.72
- **MOD版本**: 0.5.9

## 主要功能

### 🔋 多能源系统支持

#### 工业时代2(IC2):
- EU (Energy Unit):能量单元
- HU(Heat Unit):热能单元
- KU(Kinetic Unit):动能单元
#### 热力膨胀5(TE5):
- RF (Redstone Flux):红石通量
#### 神秘时代4(TC4):
- Aqua(Aqua):水元素

#### 其他
> 将蒸汽或水输送到水槽(可作为水泵)
- 蒸汽 (Steam)
- 水力 (Water)


### 🏭 机械设备
- **水力发电机**: 多种类型的水力发电设备
- **涡轮发电机**: 高效的蒸汽涡轮发电系统
- **离心机**: 用于材料分离和处理
- **各种工具**: 水力工具和设备

### 💧 储水系统
- **水库系统**: 5x5x4容量的3x3x3多方块结构水库
- **流体管理**: 完整的流体处理和传输系统

### ⛏️ 材料与矿物
#### 新增矿石: 
- 锌矿石 (6-64层,无法被活塞推动)
- 钒矿 (10-13层)
- 锰矿 (6-20层)
- 独居石 (6-32层)
- 磁铁矿 (6-64层)
#### 材料系统: 

完整的材料处理和合成系统
矿锭块、粒、矿粉、粉、小堆粉、小撮粉等多种形式

### 🔧 设备配件
- **水力裤子**: 可为IC2工具和电池充电的特殊装备
- **升级组件**: 机器升级系统
- **转子系统**: 高效的转子发电机

## 项目架构

```
src/main/kotlin/waterpower/
├── api/                    # 公共API接口
├── client/                 # 客户端代码
│   ├── gui/               # 图形界面
│   └── render/            # 渲染相关
├── common/                # 通用代码
│   ├── block/             # 方块系统
│   │   ├── machine/       # 机器方块
│   │   ├── reservoir/     # 水库系统
│   │   ├── watermill/     # 水力发电机
│   │   └── turbine/       # 涡轮系统
│   ├── item/              # 物品系统
│   ├── recipe/            # 配方系统
│   └── network/           # 网络通信
├── config/                # 配置系统
├── integration/           # 模组集成
│   ├── ic2/               # IndustrialCraft2集成
│   ├── jei/               # JEI集成
│   └── waila/             # Waila/Hwyla集成
└── util/                  # 工具类
```

## 开发环境设置

### 环境要求
- **Java版本**: Java 8 (JDK 1.8)
- **开发工具**: IntelliJ IDEA 或其他支持Kotlin的IDE
- **构建工具**: Gradle

#### 参考配置 (Windows环境)

1. **设置Java路径** :
   ```
   配置JAVA_HOME到你的JDK8安装路径,并且查看IDE是否正确的正在使用它
   ```
   
   推荐使用Adoptium Temurin项目组的[OpenJDK](https://adoptium.net/zh-CN/temurin/releases?version=8&os=any&arch=any)

2. **项目构建**:
   ```cmd
   gradlew setupDecompWorkspace
   ```

3. **编译Kotlin代码**:
   ```cmd
   gradlew compileKotlin
   ```

4. **运行开发客户端**:
   ```cmd
   gradle runClient
   ```

5. **构建最终MOD文件**:
   ```cmd
   gradlew build
   ```

### 项目依赖

#### 必需依赖前置MOD
- **Forgelin**: Kotlin语言适配器(版本最低1.5或以上)

#### 可选能源联动MOD
- **IndustrialCraft2**: 能源系统集成
- **BuildCraft**: 建筑模组集成
- **Thermal Expansion**: 热力膨胀集成
- **Mekanism**: 通用机械集成
- **JEI**: 物品配方查看
- **Waila/Hwyla**: 方块信息显示

## 使用说明

前往[MC百科](https://www.mcmod.cn/class/361.html)查看介绍

### 基础设置
1. 放置水力发电机需要邻近有效水源
2. 使用水库系统储存大量流体
3. 通过管道连接各种机械设备

### 能源转换
- 涡轮发电机高效转换蒸汽为电力
- 支持多种能源系统的互相转换

### 机器操作
- 大部分机器需要邻近水源才能工作
- 使用升级组件提升机器效率
- 通过GUI界面管理机器参数

## 开发注意事项

### 代码规范
- 使用Kotlin编写新功能
- 遵循SOLID设计原则
- 所有代码模块需要完整注释
- 添加适当的错误监控和日志

### 调试信息
- 使用WaterPower.logger记录重要信息
- 在关键位置添加错误处理
- 保持代码简单可控

### 版本兼容性
- 基于Minecraft 1.12.2
- 兼容同版本下的主流模组生态
- 由于MC MOD特性,本项目不保证任何向下兼容性,且此版本仅在MC 1.12.2上经过测试
- 由于本项目提供的机械最终目的为产生能源,因此强烈依赖需要使用能源的模组比如工业2等.而工业2模组及BuildCraft模组相对稳定的高版本均为1.12.2,所以本项目暂无考虑兼容更高版本

## 故障排除

### 常见问题
1. **编译错误**: 检查Java版本和Kotlin版本是否正确
2. **运行时错误**: 查看日志文件定位问题
3. **模组冲突**: 检查依赖模组版本兼容性

### 获取帮助
- 查看日志文件: `run/logs/latest.log`
- 检查配置文件: `run/config/`
- 参考社区资源

## 更新日志

### 2025/09/15 当前版本 0.5.9 
- Forge版本升级至14.23.5.2860
- 修复升级后的兼容性问题及编译错误


### 2025/09/12 当前版本 0.5.8 
- Forge版本升级至14.23.5.2847
- 调整Gradle和ForgeGradle的更新,保证项目恢复可编译
- 修复纹理资源异常配置造成的物品栏图标显示错误

## 开发人员

- **原开发者**: huanghongxun
- **修复维护**: Nautiloideas

## 链接

- **原项目CurseForge页面**:https://www.curseforge.com/minecraft/mc-mods/water-power
- **原项目更新下载**: https://www.curseforge.com/minecraft/mc-mods/water-power/files

---

*本文档将随项目发展持续更新，确保开发者和用户能够轻松理解和使用WaterPower模组的所有功能。*
