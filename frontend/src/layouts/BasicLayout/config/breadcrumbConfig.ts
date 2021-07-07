/*
 * @文件描述: 扩展面包屑
 * @公司: 山东大学
 * @作者: 李洪文
 * @Date: 2019-05-31 10:35:10
 * @LastEditors: liuweis
 * @LastEditTime: 2020-12-28 15:38:56
 */

export const routes = [
  {
    path: '/base',
    breadcrumbName: '基础管理',
    children: [
      {
        path: '/user',
        breadcrumbName: '用户管理',
        children: [
          { path: '/add', breadcrumbName: '新增' },
          { path: '/edit', breadcrumbName: '编辑' },
        ],
      },
      {
        path: '/product',
        breadcrumbName: '记账管理',
      },
      {
        path: '/range',
        breadcrumbName: '时间范围搜索',
      },
    ],
    
  },
  {
    path: '/system',
    breadcrumbName: '系统管理',
    children: [
      {
        path: '/config',
        breadcrumbName: '参数设置',
      },
      {
        path: '/network',
        breadcrumbName: '网络设置',
      },
      {
        path: '/product',
        breadcrumbName: '记账管理',
      },
    ],
  },
];
