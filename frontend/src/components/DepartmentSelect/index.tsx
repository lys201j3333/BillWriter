import React, { useState, useEffect, useCallback } from 'react';
import { Select } from 'antd';

interface ProductSelectProps {
  mode?: 'multiple' | 'tags';
  value?: number | number[] | undefined;
  onChange?: (value: number | number[] | undefined) => void;
}

export default function ProductSelect(props: ProductSelectProps) {
  const [productList, setProductList] = useState<defs.ProductVO[]>([]);
  const fetchProductList = useCallback(() => {
    API.product.fetch.request({}).then(list => {
      setProductList(list);
    });
  }, []);
  useEffect(() => {
    fetchProductList();
  }, []);
  return (
    <Select
      showSearch={true}
      allowClear={true}
      mode={props.mode}
      placeholder="请选择组织机构"
      defaultActiveFirstOption={false}
      showArrow={true}
      filterOption={false}
      value={props.value}
      notFoundContent={null}
      onSearch={fetchProductList}
      onChange={(value: number | number[] | undefined) => {
        if (props.onChange) props.onChange(value);
      }}
    >
      {productList.map((item: defs.ProductVO) => (
        <Select.Option key={`product-${item.id}`} value={item.id!}>
          {item.name}
        </Select.Option>
      ))}
    </Select>
  );
}
