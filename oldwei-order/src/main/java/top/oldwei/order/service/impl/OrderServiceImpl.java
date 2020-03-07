package top.oldwei.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.oldwei.order.entity.Order;
import top.oldwei.order.mapper.OrderMapper;
import top.oldwei.order.service.OrderService;

/**
 * @Author:weizd
 * @Date:20-3-7
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
