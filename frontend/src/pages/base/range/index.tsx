/*
 * @文件描述: 首页
 * @公司: 山东大学
 * @作者: 李洪文
 * @LastEditors: 李洪文
 * @Date: 2019-05-09 15:40:17
 * @LastEditTime: 2020-04-01 12:20:23
 */
import { useCallback, useEffect, useState } from 'react';
import CustomTable from '@/components/CustomTable';
import FileAddOutlined from '@ant-design/icons/FileAddOutlined';
import DeleteOutlined from '@ant-design/icons/DeleteOutlined';
import GroupOutlined from '@ant-design/icons/GroupOutlined';
import SearchFilter from './SearchFilter';
import { Divider, Modal } from 'antd';
import { ButtonItem } from '@/data-type/common';
import { DEFAULT_SEARCH_PROPS, DEFAULT_PAGE_DATA } from '@/constants';
import InputDialog from './InputDialog';
import { Column } from '@ant-design/charts';
export default function ProductPage() {
  const [searchProps, changeSearchProps] = useState<defs.ProductQueryDTO>({
    ...DEFAULT_SEARCH_PROPS,
  });

  const [pageData, setPageData] = useState<defs.Page<defs.ProductVO>>(
    DEFAULT_PAGE_DATA,
  );
  const [selectedRowKeys, selectRow] = useState<number[] | string[]>([]);
  const [visible, setVisible] = useState(false);
  const [loading, setLoading] = useState(false);
  const [product, setProduct] = useState<defs.ProductDTO | undefined>(
    undefined,
  );
  const fetchList = useCallback((props) => {
    setLoading(true);
    API.product.list2.request({}, props).then((data) => {
      setLoading(false);
      data && setPageData(data);
    });
  }, []);
  useEffect(() => {
    if (!pageData.total) {
      fetchList(searchProps);
    }
  }, []);
  const columns = [
    { title: '组ID', width: 80, dataIndex: 'id' },
    {
      title: '商品名称',
      width: 150,
      dataIndex: 'pproduct',
      render: (v: string, record: defs.ProductVO) => {
        return (
          <a
            onClick={() => {
              setProduct({ ...record });
              setVisible(true);
            }}
          >
            <GroupOutlined />
            <span style={{ marginLeft: 5 }}>{v}</span>
          </a>
        );
      },
    },
    { title: '价格',width: 100, dataIndex: 'price' },
    { title: '商品类型', width: 150,dataIndex: 'ptype' },
    { title: '描述', width: 150,dataIndex: 'description' },
    { title: '创建时间', width: 150, dataIndex: 'ctime' },
    {
      title: '操作',
      width: 120,
      render: (_: any, record: defs.ProductVO) => (
        <>
          <a
            onClick={() => {
              setProduct({ ...record });
              setVisible(true);
            }}
          >
            修改
          </a>
          <Divider type="vertical" />
          <a
            onClick={() => {
              handleDelete([`${record.id}`]);
            }}
          >
            删除
          </a>
        </>
      ),
    },
  ];

  const handleDelete = async (ids: string[] | number[]) => {
    const modal = Modal.confirm({
      centered: true,
      title: `您确定要删除选定的${ids.length}个资源组吗？`,
      okText: '确定',
      cancelText: '取消',
      onOk: async () => {
        modal.update({
          okButtonProps: {
            loading: true,
          },
        });
        const success = await API.product.remove.request(
          {},
          ids as number[],
        );
        if (success) {
          fetchList({
            ...searchProps,
            page: 1,
          });
          selectRow([]);
        }
      },
    });
  };

  const handleSave = (values: defs.ProductDTO) => {
    let result: Promise<number>;
    if (product?.id) {
      result = API.product.update.request(
        {},
        {
          id: product.id,
          ...values,
        },
      );
    } else {
      result = API.product.add.request({}, values);
    }
    result.then((success) => {
      if (success) {
        setVisible(false);
        fetchList({
          ...searchProps,
        });
      }
    });
  };

  const buttons: ButtonItem[] = [
    // {
    //   text: '新增',
    //   icon: <FileAddOutlined />,
    //   type: 'primary',
    //   onClick: () => {
    //     setProduct({});
    //     setVisible(true);
    //   },
    // },
    {
      text: '删除',
      icon: <DeleteOutlined />,
      disabled: selectedRowKeys.length === 0,
      type: 'primary',
      onClick: () => handleDelete(selectedRowKeys),
    },
  ];
  const { list, page, total } = pageData;
  return (
    <>
      <CustomTable
        loading={loading}
        columns={columns}
        buttons={buttons}
        dataSource={list || []}
        current={page}
        size="middle"
        total={total}
        genRowKey={(record: defs.ProductVO) => `${record.id}`}
        onPagination={(current: number) => {
          const newSearchProps = {
            ...searchProps,
            page: current,
          };
          changeSearchProps(newSearchProps);
          fetchList(newSearchProps);
        }}
        rowSelection={{
          columnTitle: '选择',
          columnWidth: 50,
          selectedRowKeys,
          onChange: (keys: string[]) => selectRow(keys),
        }}
      >
        <SearchFilter
          searchProps={searchProps}
          changeSearchProps={(props) => {
            changeSearchProps({
              ...searchProps,
              ...props,
            });
          }}
          onSearch={() => {
            fetchList(searchProps);
          }}
        />
      </CustomTable>
      <InputDialog
        visible={visible}
        detailData={product}
        onClose={() => setVisible(false)}
        onSubmit={handleSave}
      />
    </>
  );
}
