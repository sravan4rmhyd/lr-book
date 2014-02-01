/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.inikah.slayer.service.persistence;

import com.inikah.slayer.NoSuchPaymentException;
import com.inikah.slayer.model.Payment;
import com.inikah.slayer.model.impl.PaymentImpl;
import com.inikah.slayer.model.impl.PaymentModelImpl;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the payment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ahmed Hasan
 * @see PaymentPersistence
 * @see PaymentUtil
 * @generated
 */
public class PaymentPersistenceImpl extends BasePersistenceImpl<Payment>
	implements PaymentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PaymentUtil} to access the payment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PaymentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PaymentModelImpl.ENTITY_CACHE_ENABLED,
			PaymentModelImpl.FINDER_CACHE_ENABLED, PaymentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PaymentModelImpl.ENTITY_CACHE_ENABLED,
			PaymentModelImpl.FINDER_CACHE_ENABLED, PaymentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PaymentModelImpl.ENTITY_CACHE_ENABLED,
			PaymentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAMEID_CLASSPK =
		new FinderPath(PaymentModelImpl.ENTITY_CACHE_ENABLED,
			PaymentModelImpl.FINDER_CACHE_ENABLED, PaymentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByClassNameId_ClassPK",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID_CLASSPK =
		new FinderPath(PaymentModelImpl.ENTITY_CACHE_ENABLED,
			PaymentModelImpl.FINDER_CACHE_ENABLED, PaymentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByClassNameId_ClassPK",
			new String[] { Long.class.getName(), Long.class.getName() },
			PaymentModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			PaymentModelImpl.CLASSPK_COLUMN_BITMASK |
			PaymentModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAMEID_CLASSPK = new FinderPath(PaymentModelImpl.ENTITY_CACHE_ENABLED,
			PaymentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByClassNameId_ClassPK",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the payments where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching payments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Payment> findByClassNameId_ClassPK(long classNameId,
		long classPK) throws SystemException {
		return findByClassNameId_ClassPK(classNameId, classPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payments where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.inikah.slayer.model.impl.PaymentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of payments
	 * @param end the upper bound of the range of payments (not inclusive)
	 * @return the range of matching payments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Payment> findByClassNameId_ClassPK(long classNameId,
		long classPK, int start, int end) throws SystemException {
		return findByClassNameId_ClassPK(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the payments where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.inikah.slayer.model.impl.PaymentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param start the lower bound of the range of payments
	 * @param end the upper bound of the range of payments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching payments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Payment> findByClassNameId_ClassPK(long classNameId,
		long classPK, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID_CLASSPK;
			finderArgs = new Object[] { classNameId, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAMEID_CLASSPK;
			finderArgs = new Object[] {
					classNameId, classPK,
					
					start, end, orderByComparator
				};
		}

		List<Payment> list = (List<Payment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Payment payment : list) {
				if ((classNameId != payment.getClassNameId()) ||
						(classPK != payment.getClassPK())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_PAYMENT_WHERE);

			query.append(_FINDER_COLUMN_CLASSNAMEID_CLASSPK_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CLASSNAMEID_CLASSPK_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PaymentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				if (!pagination) {
					list = (List<Payment>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Payment>(list);
				}
				else {
					list = (List<Payment>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first payment in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment
	 * @throws com.inikah.slayer.NoSuchPaymentException if a matching payment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Payment findByClassNameId_ClassPK_First(long classNameId,
		long classPK, OrderByComparator orderByComparator)
		throws NoSuchPaymentException, SystemException {
		Payment payment = fetchByClassNameId_ClassPK_First(classNameId,
				classPK, orderByComparator);

		if (payment != null) {
			return payment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPaymentException(msg.toString());
	}

	/**
	 * Returns the first payment in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching payment, or <code>null</code> if a matching payment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Payment fetchByClassNameId_ClassPK_First(long classNameId,
		long classPK, OrderByComparator orderByComparator)
		throws SystemException {
		List<Payment> list = findByClassNameId_ClassPK(classNameId, classPK, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last payment in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment
	 * @throws com.inikah.slayer.NoSuchPaymentException if a matching payment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Payment findByClassNameId_ClassPK_Last(long classNameId,
		long classPK, OrderByComparator orderByComparator)
		throws NoSuchPaymentException, SystemException {
		Payment payment = fetchByClassNameId_ClassPK_Last(classNameId, classPK,
				orderByComparator);

		if (payment != null) {
			return payment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPaymentException(msg.toString());
	}

	/**
	 * Returns the last payment in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching payment, or <code>null</code> if a matching payment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Payment fetchByClassNameId_ClassPK_Last(long classNameId,
		long classPK, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByClassNameId_ClassPK(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<Payment> list = findByClassNameId_ClassPK(classNameId, classPK,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the payments before and after the current payment in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param paymentId the primary key of the current payment
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next payment
	 * @throws com.inikah.slayer.NoSuchPaymentException if a payment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Payment[] findByClassNameId_ClassPK_PrevAndNext(long paymentId,
		long classNameId, long classPK, OrderByComparator orderByComparator)
		throws NoSuchPaymentException, SystemException {
		Payment payment = findByPrimaryKey(paymentId);

		Session session = null;

		try {
			session = openSession();

			Payment[] array = new PaymentImpl[3];

			array[0] = getByClassNameId_ClassPK_PrevAndNext(session, payment,
					classNameId, classPK, orderByComparator, true);

			array[1] = payment;

			array[2] = getByClassNameId_ClassPK_PrevAndNext(session, payment,
					classNameId, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Payment getByClassNameId_ClassPK_PrevAndNext(Session session,
		Payment payment, long classNameId, long classPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PAYMENT_WHERE);

		query.append(_FINDER_COLUMN_CLASSNAMEID_CLASSPK_CLASSNAMEID_2);

		query.append(_FINDER_COLUMN_CLASSNAMEID_CLASSPK_CLASSPK_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(PaymentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(payment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Payment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the payments where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByClassNameId_ClassPK(long classNameId, long classPK)
		throws SystemException {
		for (Payment payment : findByClassNameId_ClassPK(classNameId, classPK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(payment);
		}
	}

	/**
	 * Returns the number of payments where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching payments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByClassNameId_ClassPK(long classNameId, long classPK)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CLASSNAMEID_CLASSPK;

		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PAYMENT_WHERE);

			query.append(_FINDER_COLUMN_CLASSNAMEID_CLASSPK_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CLASSNAMEID_CLASSPK_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CLASSNAMEID_CLASSPK_CLASSNAMEID_2 =
		"payment.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_CLASSNAMEID_CLASSPK_CLASSPK_2 = "payment.classPK = ?";

	public PaymentPersistenceImpl() {
		setModelClass(Payment.class);
	}

	/**
	 * Caches the payment in the entity cache if it is enabled.
	 *
	 * @param payment the payment
	 */
	@Override
	public void cacheResult(Payment payment) {
		EntityCacheUtil.putResult(PaymentModelImpl.ENTITY_CACHE_ENABLED,
			PaymentImpl.class, payment.getPrimaryKey(), payment);

		payment.resetOriginalValues();
	}

	/**
	 * Caches the payments in the entity cache if it is enabled.
	 *
	 * @param payments the payments
	 */
	@Override
	public void cacheResult(List<Payment> payments) {
		for (Payment payment : payments) {
			if (EntityCacheUtil.getResult(
						PaymentModelImpl.ENTITY_CACHE_ENABLED,
						PaymentImpl.class, payment.getPrimaryKey()) == null) {
				cacheResult(payment);
			}
			else {
				payment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all payments.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PaymentImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PaymentImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the payment.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Payment payment) {
		EntityCacheUtil.removeResult(PaymentModelImpl.ENTITY_CACHE_ENABLED,
			PaymentImpl.class, payment.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Payment> payments) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Payment payment : payments) {
			EntityCacheUtil.removeResult(PaymentModelImpl.ENTITY_CACHE_ENABLED,
				PaymentImpl.class, payment.getPrimaryKey());
		}
	}

	/**
	 * Creates a new payment with the primary key. Does not add the payment to the database.
	 *
	 * @param paymentId the primary key for the new payment
	 * @return the new payment
	 */
	@Override
	public Payment create(long paymentId) {
		Payment payment = new PaymentImpl();

		payment.setNew(true);
		payment.setPrimaryKey(paymentId);

		return payment;
	}

	/**
	 * Removes the payment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param paymentId the primary key of the payment
	 * @return the payment that was removed
	 * @throws com.inikah.slayer.NoSuchPaymentException if a payment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Payment remove(long paymentId)
		throws NoSuchPaymentException, SystemException {
		return remove((Serializable)paymentId);
	}

	/**
	 * Removes the payment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the payment
	 * @return the payment that was removed
	 * @throws com.inikah.slayer.NoSuchPaymentException if a payment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Payment remove(Serializable primaryKey)
		throws NoSuchPaymentException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Payment payment = (Payment)session.get(PaymentImpl.class, primaryKey);

			if (payment == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPaymentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(payment);
		}
		catch (NoSuchPaymentException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Payment removeImpl(Payment payment) throws SystemException {
		payment = toUnwrappedModel(payment);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(payment)) {
				payment = (Payment)session.get(PaymentImpl.class,
						payment.getPrimaryKeyObj());
			}

			if (payment != null) {
				session.delete(payment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (payment != null) {
			clearCache(payment);
		}

		return payment;
	}

	@Override
	public Payment updateImpl(com.inikah.slayer.model.Payment payment)
		throws SystemException {
		payment = toUnwrappedModel(payment);

		boolean isNew = payment.isNew();

		PaymentModelImpl paymentModelImpl = (PaymentModelImpl)payment;

		Session session = null;

		try {
			session = openSession();

			if (payment.isNew()) {
				session.save(payment);

				payment.setNew(false);
			}
			else {
				session.merge(payment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PaymentModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((paymentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID_CLASSPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						paymentModelImpl.getOriginalClassNameId(),
						paymentModelImpl.getOriginalClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEID_CLASSPK,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID_CLASSPK,
					args);

				args = new Object[] {
						paymentModelImpl.getClassNameId(),
						paymentModelImpl.getClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEID_CLASSPK,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID_CLASSPK,
					args);
			}
		}

		EntityCacheUtil.putResult(PaymentModelImpl.ENTITY_CACHE_ENABLED,
			PaymentImpl.class, payment.getPrimaryKey(), payment);

		return payment;
	}

	protected Payment toUnwrappedModel(Payment payment) {
		if (payment instanceof PaymentImpl) {
			return payment;
		}

		PaymentImpl paymentImpl = new PaymentImpl();

		paymentImpl.setNew(payment.isNew());
		paymentImpl.setPrimaryKey(payment.getPrimaryKey());

		paymentImpl.setPaymentId(payment.getPaymentId());
		paymentImpl.setClassNameId(payment.getClassNameId());
		paymentImpl.setClassPK(payment.getClassPK());
		paymentImpl.setUserId(payment.getUserId());
		paymentImpl.setPlanId(payment.getPlanId());
		paymentImpl.setAmount(payment.getAmount());
		paymentImpl.setPaymentMode(payment.getPaymentMode());
		paymentImpl.setDetails(payment.getDetails());
		paymentImpl.setPaid(payment.isPaid());
		paymentImpl.setCreateDate(payment.getCreateDate());
		paymentImpl.setModifiedDate(payment.getModifiedDate());

		return paymentImpl;
	}

	/**
	 * Returns the payment with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the payment
	 * @return the payment
	 * @throws com.inikah.slayer.NoSuchPaymentException if a payment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Payment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPaymentException, SystemException {
		Payment payment = fetchByPrimaryKey(primaryKey);

		if (payment == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPaymentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return payment;
	}

	/**
	 * Returns the payment with the primary key or throws a {@link com.inikah.slayer.NoSuchPaymentException} if it could not be found.
	 *
	 * @param paymentId the primary key of the payment
	 * @return the payment
	 * @throws com.inikah.slayer.NoSuchPaymentException if a payment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Payment findByPrimaryKey(long paymentId)
		throws NoSuchPaymentException, SystemException {
		return findByPrimaryKey((Serializable)paymentId);
	}

	/**
	 * Returns the payment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the payment
	 * @return the payment, or <code>null</code> if a payment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Payment fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Payment payment = (Payment)EntityCacheUtil.getResult(PaymentModelImpl.ENTITY_CACHE_ENABLED,
				PaymentImpl.class, primaryKey);

		if (payment == _nullPayment) {
			return null;
		}

		if (payment == null) {
			Session session = null;

			try {
				session = openSession();

				payment = (Payment)session.get(PaymentImpl.class, primaryKey);

				if (payment != null) {
					cacheResult(payment);
				}
				else {
					EntityCacheUtil.putResult(PaymentModelImpl.ENTITY_CACHE_ENABLED,
						PaymentImpl.class, primaryKey, _nullPayment);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(PaymentModelImpl.ENTITY_CACHE_ENABLED,
					PaymentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return payment;
	}

	/**
	 * Returns the payment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param paymentId the primary key of the payment
	 * @return the payment, or <code>null</code> if a payment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Payment fetchByPrimaryKey(long paymentId) throws SystemException {
		return fetchByPrimaryKey((Serializable)paymentId);
	}

	/**
	 * Returns all the payments.
	 *
	 * @return the payments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Payment> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the payments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.inikah.slayer.model.impl.PaymentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of payments
	 * @param end the upper bound of the range of payments (not inclusive)
	 * @return the range of payments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Payment> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the payments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.inikah.slayer.model.impl.PaymentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of payments
	 * @param end the upper bound of the range of payments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of payments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Payment> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Payment> list = (List<Payment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PAYMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PAYMENT;

				if (pagination) {
					sql = sql.concat(PaymentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Payment>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Payment>(list);
				}
				else {
					list = (List<Payment>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the payments from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Payment payment : findAll()) {
			remove(payment);
		}
	}

	/**
	 * Returns the number of payments.
	 *
	 * @return the number of payments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PAYMENT);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the payment persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.inikah.slayer.model.Payment")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Payment>> listenersList = new ArrayList<ModelListener<Payment>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Payment>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(PaymentImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_PAYMENT = "SELECT payment FROM Payment payment";
	private static final String _SQL_SELECT_PAYMENT_WHERE = "SELECT payment FROM Payment payment WHERE ";
	private static final String _SQL_COUNT_PAYMENT = "SELECT COUNT(payment) FROM Payment payment";
	private static final String _SQL_COUNT_PAYMENT_WHERE = "SELECT COUNT(payment) FROM Payment payment WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "payment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Payment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Payment exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(PaymentPersistenceImpl.class);
	private static Payment _nullPayment = new PaymentImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Payment> toCacheModel() {
				return _nullPaymentCacheModel;
			}
		};

	private static CacheModel<Payment> _nullPaymentCacheModel = new CacheModel<Payment>() {
			@Override
			public Payment toEntityModel() {
				return _nullPayment;
			}
		};
}