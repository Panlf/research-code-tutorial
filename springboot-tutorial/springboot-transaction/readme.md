# Spring事务
## 事务概念
Spring事务主要分为编程式事务和声明式事务。

### 编程式事务
指在代码中手动的管理事务的提交、回滚等操作。

### 声明式事务
基于AOP面向切面的，将具体业务与事务处理部分解耦。声明式事务也有两种实现方式，一是基于TX和AOP的xml配置文件方式，二是使用@Transactional注解。

#### 注解的使用方式
##### @Transactional的注解地方
可以注解在接口、类、类方法

- 类 表示所有该类的public方法都配置相同的事务属性信息
- 类方法 方法的事务属性配置会覆盖类方法的配置属性
- 接口 不推荐使用，动态代理会使得注解失效。

##### @Transactional注解的属性
###### propagation属性
propagation 代表事务的传播行为，默认值为 Propagation.REQUIRED，其他的属性信息如下：
- Propagation.REQUIRED : 如果当前存在事务，则加入该事务，如果当前不存在事务，则创建一个新的事务。( 也就是说如果A方法和B方法都添加了注解，在默认传播模式下，A方法内部调用B方法，会把两个方法的事务合并为一个事务 ）
- Propagation.SUPPORTS : 如果当前存在事务，则加入该事务；如果当前不存在事务，则以非事务的方式继续运行。
- Propagation.MANDATORY : 如果当前存在事务，则加入该事务；如果当前不存在事务，则抛出异常。
- Propagation.REQUIRES_NEW : 重新创建一个新的事务，如果当前存在事务，暂停当前的事务。( 当类A中的 a 方法用默认Propagation.REQUIRED模式，类B中的 b方法加上采用 Propagation.REQUIRES_NEW模式，然后在 a 方法中调用 b方法操作数据库，然而 a方法抛出异常后，b方法并没有进行回滚，因为Propagation.REQUIRES_NEW会暂停 a方法的事务 )
- Propagation.NOT_SUPPORTED : 以非事务的方式运行，如果当前存在事务，暂停当前的事务。
- Propagation.NEVER : 以非事务的方式运行，如果当前存在事务，则抛出异常。
- Propagation.NESTED : 和 Propagation.REQUIRED 效果一样。

###### isolation 属性
isolation ：事务的隔离级别，默认值为 Isolation.DEFAULT。

- Isolation.DEFAULT：使用底层数据库默认的隔离级别。
- Isolation.READ_UNCOMMITTED
- Isolation.READ_COMMITTED
- Isolation.REPEATABLE_READ
- Isolation.SERIALIZABLE

###### timeout 属性
timeout : 事务的超时时间，默认值为 -1。如果超过该时间限制但事务还没有完成，则自动回滚事务。

###### readOnly 属性
readOnly : 指定事务是否为只读事务，默认值为 false；为了忽略那些不需要事务的方法，比如读取数据，可以设置 read-only 为 true。

###### rollbackFor 属性
rollbackFor : 用于指定能够触发事务回滚的异常类型，可以指定多个异常类型。

###### noRollbackFor属性
noRollbackFor : 抛出指定的异常类型，不回滚事务，也可以指定多个异常类型。

## Spring事务@Transactional注解不生效场景
### 1、数据库引擎不支持事务
比如MySQL默认使用支持事务的InnoDB引擎，如果切换成MyISAM引擎那就不支持事务了。

### 2、类方法没有被Spring管理
若Service方法没有使用@Serviec注解，这个类就不会被加载成一个Bean，这样这个类就也不会被Spring管理，事务自然就失效了。

### 3、方法不是public
Spring在代理的时候，获取Transactional 注解的事务配置信息时候会检查目标方法的修饰符是否为 public，不是 public则不会获取@Transactional 的属性配置信息。
注意：protected、private 修饰的方法上使用 @Transactional 注解，虽然事务无效，但不会有任何报错。

### 4、同一个方法调用错误
如果A方法没有声明注解事务，而B方法有，则在A方法中调用B方法，则B方法的事务也会失效

### 5、异常被catch
方法B声明事务，但是方法A中调用方法B时候，使用try/catch了就同样导致事务失效。是因为因为当B方法中抛出了一个异常以后，B方法标识当前事务需要rollback。但是A方法中由于手动的捕获这个异常并进行处理，A方法认为当前事务应该正常commit。此时就出现了前后不一致，也就是因为这样抛出了UnexpectedRollbackException异常。

### 6、异常类型错误
rollbackFor 可以指定能够触发事务回滚的异常类型。Spring默认抛出了未检查unchecked异常（继承自 RuntimeException 的异常）或者 Error才回滚事务；其他异常不会触发回滚事务。如果在事务中抛出其他类型的异常，但却期望 Spring 能够回滚事务，就需要指定rollbackFor属性。