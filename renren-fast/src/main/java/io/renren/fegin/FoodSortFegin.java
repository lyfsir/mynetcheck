package io.renren.fegin;

import io.renren.common.utils.R;
import io.renren.modules.food.vo.FoodSpecialVo;
import io.renren.modules.food.vo.SortVo;
import io.renren.modules.food.vo.UpdatefoodVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author lyf
 * @date 2020/8/4-2:05
 */
@FeignClient("lyf-food")
public interface FoodSortFegin {
    @RequestMapping("food/foodinfo/list")
    public R list(@RequestParam Map<String, Object> params);
    /**
     * 删除
     */
    @RequestMapping("food/sort/delete")
    public R delete(@RequestBody Integer[] ids);

    @RequestMapping("food/sort/list/tree")
    public R getTreeList();

    /**
     * 修改
     */
    @RequestMapping("food/sort/update")
    public R update(@RequestBody SortVo sort);

    /**
     * 保存
     */
    @RequestMapping("food/sort/save")
    public R save(@RequestBody SortVo sort);

/**
 * -----info--------
 */
    /**
     * 获取auditing为1的分页列表(待审核)
     */
    @RequestMapping("food/foodinfo/list/{num}")
    public R list(@RequestParam Map<String, Object> params,
                                       @PathVariable Integer num);

    //查询食物的详情信息
    @RequestMapping("food/foodinfo/select/infomation")
    public R selects(@RequestParam Integer foodId);

    /**
     * 保存审核通过的信息
     */
    @RequestMapping("food/foodinfo/save")
    public R save(@RequestBody UpdatefoodVo updatefoodVo);

    /**
     * 删除
     */
    @RequestMapping("food/foodinfo/delete")
    public R deletes(@RequestBody Integer[] ids);


/**
 * ------专题---------
 */
    /**
     * 获取使用专题名字信息
     */
    @GetMapping("food/foodspecial/info/name")
    public R doGet();

    /**
     * 列表
     */
    @RequestMapping("food/foodspecial/list")
    public R listto(@RequestParam Map<String, Object> params);

    /**
     * 保存
     */
    @RequestMapping("food/foodspecial/save")
    public R save(@RequestBody FoodSpecialVo vo);

    /**
     * 信息
     */
    @RequestMapping("food/foodspecial/info/{id}")
    public R info(@PathVariable("id") Integer id);

    /**
     * 修改
     */
    @RequestMapping("food/foodspecial/update")
    public R update(@RequestBody FoodSpecialVo vo);

    /**
     * 删除
     */
    @RequestMapping("food/foodspecial/delete")
    public R deletesp(@RequestBody Integer[] ids);

}
