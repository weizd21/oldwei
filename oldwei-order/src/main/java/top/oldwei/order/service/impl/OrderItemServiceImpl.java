package top.oldwei.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.oldwei.order.entity.OrderItem;
import top.oldwei.order.mapper.OrderItemMapper;
import top.oldwei.order.service.OrderItemService;

/**
 * @Author:weizd
 * @Date:20-3-7
 */
@Service
@Slf4j
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {


}
